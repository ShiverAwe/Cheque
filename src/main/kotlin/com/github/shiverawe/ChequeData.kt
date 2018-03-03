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
        val smena: String = "315",
        /**
         * Номер чека в смене
         */
        val docNumb: String = "411"
) {}