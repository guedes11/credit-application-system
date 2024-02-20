package dev.kevem.request.credit.system.dto

import dev.kevem.request.credit.system.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(

    @field:NotEmpty(message = "Primeiro nome não pode estar em branco.")
    val firstName: String,

    @field:NotEmpty(message = "Primeiro nome não pode estar em branco.")
    val lastName: String,

    @field:NotNull(message = "Valor inválido.")
    val income: BigDecimal,

    @field:NotEmpty(message = "Primeiro nome não pode estar em branco.")
    val zipCode: String,

    @field:NotEmpty(message = "Primeiro nome não pode estar em branco.")
    val street: String
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipCode = this.zipCode
        return customer
    }
}
