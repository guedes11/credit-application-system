package dev.kevem.request.credit.system.service

import dev.kevem.request.credit.system.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)
}