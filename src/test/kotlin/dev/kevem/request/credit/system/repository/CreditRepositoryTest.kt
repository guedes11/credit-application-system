package dev.kevem.request.credit.system.repository

import dev.kevem.request.credit.system.entity.Address
import dev.kevem.request.credit.system.entity.Credit
import dev.kevem.request.credit.system.entity.Customer
import io.mockk.every
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.UUID

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {
    @Autowired
    lateinit var creditRepository: CreditRepository
    @Autowired
    lateinit var testEntityManager: TestEntityManager

    private lateinit var customer: Customer
    private lateinit var credit: Credit
    private lateinit var credit1: Credit

    @BeforeEach
    fun setup(){
        customer = testEntityManager.persist(buildCustomer())
        credit = testEntityManager.persist(buildCredit(customer = customer))
        credit1 = testEntityManager.persist(buildCredit(customer = customer))

    }

    @Test
    fun should_find_credit_by_creditCode(){
        //given
        val creditCode = UUID.fromString("aa547c0f-9a6a-451f-8c89-afddce916a29")
        val creditCode1 = UUID.fromString("49f740be-46a7-449b-84e7-ff5b7986d7ef")
        credit.creditCode = creditCode
        credit1.creditCode = creditCode1
        //when
        val fakeCredit: Credit = creditRepository.findByCreditCode(creditCode)
        val fakeCredit1: Credit = creditRepository.findByCreditCode(creditCode1)
        //then
        Assertions.assertThat(fakeCredit).isNotNull
        Assertions.assertThat(fakeCredit1).isNotNull
        Assertions.assertThat(fakeCredit).isSameAs(credit)
        Assertions.assertThat(fakeCredit1).isSameAs(credit1)

    }

    @Test
    fun should_find_all_credits_by_customer_id(){
        //given
        val customerId = 1L
        //when
        val creditList: List<Credit> = creditRepository.findAllByCustomerId(customerId)
        //then
        Assertions.assertThat(creditList).isNotEmpty
        Assertions.assertThat(creditList.size).isEqualTo(2)
        Assertions.assertThat(creditList).contains(credit, credit1)
    }

    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.of(2024, Month.APRIL, 22),
        numberOfInstallment: Int = 5,
        customer: Customer
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallment = numberOfInstallment,
        customer = customer
    )

    private fun buildCustomer(
        firstName: String = "Marcela",
        lastName: String = "Souza",
        cpf: String = "67392446851",
        email: String = "Marcela@live.com",
        password: String = "fjiosnols454",
        zipCode: String = "01532952",
        street: String = "Rua Graviola",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
    )

}