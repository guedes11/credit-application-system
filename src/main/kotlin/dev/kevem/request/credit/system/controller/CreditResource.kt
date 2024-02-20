package dev.kevem.request.credit.system.controller

import dev.kevem.request.credit.system.dto.CreditDto
import dev.kevem.request.credit.system.dto.CreditView
import dev.kevem.request.credit.system.dto.CreditViewList
import dev.kevem.request.credit.system.entity.Credit
import dev.kevem.request.credit.system.service.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

//RECEBE OS DADOS DO FRONT-END

@RestController
@RequestMapping("/api/credits")
class CreditResource(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String>{
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Credit ${credit.creditCode} - Customer ${credit.customer?.email} saved!")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>>{
        return ResponseEntity.status(HttpStatus.OK).body(this.creditService.findAllByCostumer(customerId).stream().map { credit: Credit -> CreditViewList(credit) }.collect(
            Collectors.toList()))

    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long,
                         @PathVariable creditCode: UUID
    ): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}