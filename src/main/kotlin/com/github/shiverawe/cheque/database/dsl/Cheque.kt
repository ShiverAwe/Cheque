package com.github.shiverawe.cheque.database.dsl

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Cheque(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Cheque>(Cheques)
    var inn by Cheques.inn
    var kkt by Cheques.kkt
    var ofd by Cheques.ofd
    var fn by Cheques.fn
    var fd by Cheques.fd
    var fp by Cheques.fp
    var shiftNumber by Cheques.shiftNumber
    var shiftDocNumber by Cheques.shiftDocNumber
    var date by Cheques.date
    var summary by Cheques.summary
}