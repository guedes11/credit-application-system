package dev.kevem.request.credit.system.controller

import dev.kevem.request.credit.system.dto.CustomerDto
import dev.kevem.request.credit.system.dto.CustomerUpdateDto
import dev.kevem.request.credit.system.dto.CustomerView
import dev.kevem.request.credit.system.entity.Customer
import dev.kevem.request.credit.system.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//RECEBE OS DADOS DO FRONT-END

@RestController
@RequestMapping("/api/customers")
class CustomerResource(private val customerService: CustomerService) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<CustomerView> {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerView(savedCustomer))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) {
        return this.customerService.delete(id)
    }

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {

        val customer: Customer = this.customerService.findById(id)
        val customerToUpdate: Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(customerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }
}