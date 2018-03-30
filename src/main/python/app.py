import json

from flask import Flask, request

from taxcom import Taxcom

application = Flask(__name__)


@application.route("/get", methods=['GET'])
def get_info():
    fp = request.args.get("fp")
    summ = request.args.get("summary")

    tx = Taxcom(fiscal_id=fp, raw_sum=summ)
    result = dict()
    if tx.search():
        result['items'] = tx.get_items()
    if tx.errors:
        result['error_message'] = tx.errors

    return json.dumps(result, ensure_ascii=False, indent=4, separators=(',', ': '))


if __name__ == "__main__":
    application.run()
