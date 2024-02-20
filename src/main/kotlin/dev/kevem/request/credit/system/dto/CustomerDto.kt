package dev.kevem.request.credit.system.dto

import dev.kevem.request.credit.system.entity.Address
import dev.kevem.request.credit.system.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(

    @field:NotEmpty(message = "Primeiro nome não pode estar em branco.")
    val firstName: String,

    @field:NotEmpty(message = "Segundo nome não pode estar em branco.")
    val lastName: String,

    @field:NotEmpty(message = "CPF não pode estar em branco.")
    @field:CPF(message = "CPF inválido.")
    val cpf: String,

    @field:NotNull(message = "Valor inválido.")
    val income: BigDecimal,

    @field:NotEmpty(message = "Email não pode estar em branco.")
    @field:Email(message = "Email inválido.")
    val email: String,

    @field:NotEmpty(message = "Senha não pode estar em branco.")
    val password: String,

    @field:NotEmpty(message = "CEP não pode estar em branco.")
    val zipCode: String,

    @field:NotEmpty(message = "Rua não pode estar em branco.")
    val street: String
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}
