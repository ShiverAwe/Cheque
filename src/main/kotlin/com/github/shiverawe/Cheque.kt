package com.github.shiverawe

/**
 * Класс, хранящий исключительную информацию о чеке
 * Возвращаемый JSON может быть разпознан этой структурой
 */
data class Cheque
(
        val cashTotalSum: Number? = null,
        val dateTime: Number? = null,
        val discount: Any? = null,
        val discountSum: Any? = null,
        val ecashTotalSum: Number? = null,
        val fiscalDocumentNumber: Number? = null,
        val fiscalDriveNumber: String? = null,
        val fiscalSign: Number? = null,
        val items: List<Item>? = null,
        val kktNumber: Any? = null,
        val kktRegId: String? = null,
        val markup: Any? = null,
        val markupSum: Any? = null,
        val modifiers: List<Any>? = null,
        val nds0: Any? = null,
        val nds10: Any? = null,
        val nds18: Number? = null,
        val ndsCalculated10: Any? = null,
        val ndsCalculated18: Any? = null,
        val ndsNo: Any? = null,
        val operationType: Number? = null,
        val operator: String? = null,
        val requestNumber: Number? = null,
        val retailPlaceAddress: String? = null,
        val shiftNumber: Number? = null,
        val stornoItems: List<Any>? = null,
        val taxationType: Number? = null,
        val totalSum: Number? = null,
        val user: String? = null,
        val userInn: String? = null
)

data class Item
(
        val modifiers: List<Any>? = null,
        val name: String? = null,
        val nds0: Any? = null,
        val nds10: Any? = null,
        val nds18: Number? = null,
        val ndsCalculated10: Any? = null,
        val ndsCalculated18: Any? = null,
        val ndsNo: Any? = null,
        val price: Number? = null,
        val quantity: Number? = null,
        val sum: Number? = null,
        val storno: Boolean? = null
)
