#!/usr/bin/env python
# -*- coding: utf-8 -*-
import sys
import argparse
import json

import requests
from bs4 import BeautifulSoup


class Taxcom:
    url_receipt_get = "https://receipt.taxcom.ru/v01/show?fp={}&s={}"

    def __init__(self, fiscal_id, raw_sum):
        self.fiscal_id = fiscal_id
        self.raw_sum = raw_sum

    @staticmethod
    def is_suitable(data):
        return data['fiscal_id'] and not data['kkt']

    def search(self):
        print("Search in Taxcom...")
        request = requests.get(self.url_receipt_get.format(
            self.fiscal_id, self.raw_sum))
        if "Такой чек не найден" in request.content:
            print("Not found!")
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
                return row_obj.find_all('span')[0].get_text().encode("utf-8")

            def extract_price(row_obj):
                return row_obj.find_all('span')[1].get_text().encode("utf-8")

            items = []
            for i, row in enumerate(rows):

                name = row.get_text().strip().encode("utf-8")

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

            print("Items total sum: {}".format(self.total_sum))
            self.total_sum = "{0:.2f}".format(self.total_sum)
            if self.total_sum != self.raw_sum:
                print("WARNING! Manually calculated sum {} is not equal to the receipt sum {}!".format(
                    self.total_sum, self.raw_sum))

            self.items = items
            return items
        else:
            print("No receipt data!")
            return False


def create_parser():
    parser = argparse.ArgumentParser()
    parser.add_argument('-fp', type=int, help='фискальная подпись документа')
    parser.add_argument('-s', help='итоговая сумма чека')
    return parser


if __name__ == "__main__":
    parser = create_parser()
    namespace = parser.parse_args()
    if namespace.fp and namespace.s:
        tx = Taxcom(fiscal_id=namespace.fp, raw_sum=namespace.s)
        if tx.search():
            items = tx.get_items()
            json1 = json.dumps(dict(items), ensure_ascii=False)
            sys.stdout.write(json1)
