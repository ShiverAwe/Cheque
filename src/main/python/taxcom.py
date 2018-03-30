import requests
from bs4 import BeautifulSoup


class Taxcom:
    url_receipt_get = "https://receipt.taxcom.ru/v01/show?fp={}&s={}"

    def __init__(self, fiscal_id, raw_sum):
        self.fiscal_id = fiscal_id
        self.raw_sum = raw_sum
        self.errors = []

    @staticmethod
    def is_suitable(data):
        return data['fiscal_id'] and not data['kkt']

    def search(self):
        request = requests.get(self.url_receipt_get.format(
            self.fiscal_id, self.raw_sum))
        if request.status_code != 200:
            self.errors.append("WARNING! {}: {}".format(request.status_code, request.reason))
            return False
        elif "Такой чек не найден" in request.content.decode('utf-8'):
            self.errors.append("Check not found!")
            return False
        else:
            self.receipt_data = request.content
            return True

    def get_items(self):
        if self.receipt_data:
            soup = BeautifulSoup(self.receipt_data, "lxml")
            rows = soup.select("td.position")[:-1]
            price_counts = soup.select("tr.result")
            self.total_sum = 0

            def extract_count(row_obj):
                return row_obj.find_all('span')[0].get_text()

            def extract_price(row_obj):
                return row_obj.find_all('span')[1].get_text()

            items = []
            for i, row in enumerate(rows):

                name = row.get_text().strip()

                price = float(extract_price(price_counts[i]).replace(',', '.'))
                count = float(extract_count(price_counts[i]).replace(',', '.'))
                summa = price * count
                self.total_sum += summa
                if count != 1:
                    items.append(
                        ("{} ({} * {})".format(name, price, count),
                         "-{0:.2f}".format(summa)))
                else:
                    items.append((name, "-{0:.2f}".format(summa)))

            self.total_sum = "{0:.2f}".format(self.total_sum)
            if self.total_sum != self.raw_sum:
                self.errors.append("WARNING! Manually calculated sum {} is not equal to the receipt sum {}!".format(
                    self.total_sum, self.raw_sum))

            self.items = items
            return items
        else:
            self.errors.append("No receipt data!")
            return False
