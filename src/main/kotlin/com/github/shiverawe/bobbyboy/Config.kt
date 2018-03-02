package com.github.shiverawe.bobbyboy

//!/usr/bin/python
// -*- coding: utf-8 -*-

// настройки интеграции с Дребеденьгами
val username = "demo@example.com"
val password = "demo"
val api_key = "demo_api"            // не используется

// настройки автоопределения по тратам
val currency_name = "RUB"           // валюта для сохранения данных по умолчанию

val payment_method = {
    "default": "Кошелёк",       // место хранения и траты денег по умолчанию
    "sms_based": {              // определение по подстрокам на основе текста СМС (для карт)
        "VISA1234": "VISA",
        "Karta *1234": "MASTER CARD"
    }
}

val category_name = "Продукты"      // категория покупок по умолчанию

// строка запуска приложения для редактирования CSV перед отправлением
val edit_cmdline = "libreoffice"

// настройки сканирования и сохранения
val camera_number = 1               // порядковый номер камеры в системе
val qr_scan_waiting = 0.1           // пауза взятия скриншотов, оптимально 0.1 с
val receipt_dir = "receipts"        // директория для сохранения данных по чекам
val report_dir = "reports"          // директория для сохранения CSV для импорта
val already_recognized_send = false // разрешение передавать на сервер уже сохранённые локально чеки

// отладка для расширенного отображения ошибок
val debug = false