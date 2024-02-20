package dev.kevem.request.credit.system.dto

import dev.kevem.request.credit.system.entity.Credit
import dev.kevem.request.credit.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val creditValue: BigDecimal,
    val dayFirstInstallment: LocalDate,
    val numberOfInstallment: Int,
    val customerId: Long
) {
    fun toEntity(): Credit = Credit (
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallment = this.numberOfInstallment,
        customerId = Customer(id = this.customerId)
    )
}
