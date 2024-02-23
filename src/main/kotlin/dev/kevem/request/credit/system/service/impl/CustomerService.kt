package dev.kevem.request.credit.system.service.impl

import dev.kevem.request.credit.system.entity.Customer
import dev.kevem.request.credit.system.exception.BusinessException
import dev.kevem.request.credit.system.repository.CustomerRepository
import dev.kevem.request.credit.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService( private val customerRepository: CustomerRepository): ICustomerService {
    override fun save(customer: Customer): Customer {
        return this.customerRepository.save(customer)
    }

    override fun findById(id: Long): Customer {
        return this.customerRepository.findById(id).orElseThrow{throw BusinessException("Id $id not found") }
    }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}