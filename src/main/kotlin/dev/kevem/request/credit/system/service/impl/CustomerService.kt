package dev.kevem.request.credit.system.service.impl

import dev.kevem.request.credit.system.entity.Customer
import dev.kevem.request.credit.system.repository.CustomerRepository
import dev.kevem.request.credit.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService( private val customerRepository: CustomerRepository): ICustomerService {
    override fun save(customer: Customer): Customer {
        return this.customerRepository.save(customer)
    }

    override fun findById(id: Long): Customer {
        return this.customerRepository.findById(id).orElseThrow{throw RuntimeException("id $id not found")}
    }

    override fun delete(id: Long) {
        this.customerRepository.deleteById(id)
    }
}