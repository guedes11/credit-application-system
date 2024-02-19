package dev.kevem.request.credit.system.service

import dev.kevem.request.credit.system.entity.Credit
import java.util.UUID


interface ICreditService {
    fun save(credit: Credit): Credit
    fun findAllByCostumer(customerId: Long): List<Credit>
    fun findByCreditCode(customerId: Long, creditCode: UUID): Credit?
}