package dev.kevem.request.credit.system.dto

import dev.kevem.request.credit.system.entity.Credit
import dev.kevem.request.credit.system.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(

    @field:NotNull(message = "Valor inválido.")
    val creditValue: BigDecimal,


    @field:Future
    val dayFirstInstallment: LocalDate,

    @field:Min(1)
    @field:Max(48)
    val numberOfInstallment: Int,

    @field:NotNull(message = "Valor inválido.")
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallment = this.numberOfInstallment,
        customer = Customer(id = this.customerId)
    )
}
