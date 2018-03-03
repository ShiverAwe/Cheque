package com.github.shiverawe

data class ChequeData(
        /**
         * 10 chars number
         * Число из 10 знаков
         */
        val inn: String = "7728029110",
        /**
         * Сash-desk registration number
         * 16 chars number
         *
         * Число из 16 знаков
         */
        val kkt: String = "0000113865043432",
        /**
         * fnnumber
         * 16 chars number
         * ФН - Номер фискального накопителя
         * Число из 16 знаков
         */
        val fn: String = "8710000100257633",
        /**
         * ФД - Номер фискального документа
         * Число из 6 знаков
         */
        val fd: String = "108885",
        /**
         * ФП - Фискальный признак документы
         * Число из 10 знаков
         */
        val fp: String = "1924949121",
        /**
         * Номер смены
         */
        val shiftNumber: String = "315",
        /**
         * Номер чека в смене
         */
        val shiftDocNumber: String = "411",
        /**
         * Сумма чека.
         * 123 рубля 45 коп. -> 12345.
         * 17 рублей 00 коп. -> 1700.
         */
        val summary: Int = 178156,
        /**
         * Дата и время чека.
         * Формат гггг-мм-ддTчч:мм:сс
         * Если секунды не указаны - укажите :00
         */
        val date: String = "2018-02-23T23:45:00"
)