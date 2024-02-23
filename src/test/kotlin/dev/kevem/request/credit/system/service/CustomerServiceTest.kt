package dev.kevem.request.credit.system.service

import dev.kevem.request.credit.system.entity.Address
import dev.kevem.request.credit.system.entity.Customer
import dev.kevem.request.credit.system.exception.BusinessException
import dev.kevem.request.credit.system.repository.CustomerRepository
import dev.kevem.request.credit.system.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK
    lateinit var customerRepository: CustomerRepository
    @InjectMockKs
    lateinit var customerService: CustomerService

    @Test
    fun should_create_customer() {
        //given
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer
        //when
        var actual: Customer = customerService.save(fakeCustomer)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    @Test
    fun should_find_customer(){
        //given
        val fakeId: Long = Random.nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        //when
        val actual: Customer = customerService.findById(fakeId)
        //then
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun should_not_find_customer_by_invalid_id_and_throw_businessexception(){
        //given
        val fakeId: Long = Random.nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun should_delete_costumer_by_id(){
        //given
        val fakeId: Long = Random.nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs
        //when
        customerService.delete(fakeId)
        //then
        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }

    private fun buildCustomer(
        firstName: String = "Jose",
        lastName: String = "Guedes",
        cpf: String = "66171274399",
        email: String = "Jose@live.com",
        password: String = "Jose1454",
        zipCode: String = "54298023",
        street: String = "Rua Bom Jesus",
        income: BigDecimal = BigDecimal.valueOf(1000),
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street
        ),
        income = income,
        id = id
    )


}