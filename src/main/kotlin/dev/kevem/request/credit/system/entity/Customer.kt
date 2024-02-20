package dev.kevem.request.credit.system.entity

import dev.kevem.request.credit.system.entity.Address
import dev.kevem.request.credit.system.entity.Credit
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
data class Customer(
    @Column(nullable = false)
    var firstName: String = "",

    @Column(nullable = false)
    var lastName: String = "",

    @Column(nullable = false, unique = true)
    var cpf: String = "",

    @Column(nullable = false, unique = true)
    var income: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false, unique = true)
    var email: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = false)
    @Embedded
    var address: Address = Address(),

    @Column(nullable = false)
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE], mappedBy = "customer")
    var credit: List<Credit> = mutableListOf(),

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
