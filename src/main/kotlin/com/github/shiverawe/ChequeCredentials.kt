package com.github.shiverawe

data class ChequeCredentials(
        /**
         * 10 chars number
         * ИНН
         * Число из 10 знаков
         */
        val inn: String = "",
        /**
         * Сash-desk registration number
         * 16 chars number
         *
         * Число из 16 знаков
         */
        val kkt: String = "",
        /**
         * fnnumber
         * 16 chars number
         * ФН - Номер фискального накопителя
         * Число из 16 знаков
         */
        val fn: String = "",
        /**
         * RawID
         * ФД - Номер фискального документа
         * Число из 6 знаков
         */
        val fd: String = "",
        /**
         * ФП - Фискальный признак документы
         * Число из 10 знаков
         */
        val fp: String = "",
        /**
         * Номер смены
         */
        val shiftNumber: String = "",
        /**
         * Номер чека в смене
         */
        val shiftDocNumber: String = "",
        /**
         * Сумма чека.
         * 123 рубля 45 коп. -> 12345.
         * 17 рублей 00 коп. -> 1700.
         */
        val summary: Int = 0,
        /**
         * Дата и время чека.
         * Формат гггг-мм-ддTчч:мм:сс
         * Если секунды не указаны - укажите :00
         */
        val date: String = ""
)