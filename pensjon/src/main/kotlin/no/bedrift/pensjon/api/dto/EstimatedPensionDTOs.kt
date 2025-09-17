package no.bedrift.pensjon.api.dto

import java.math.BigDecimal
import java.time.LocalDate

data class EstimatedPensionResponse(
    val employeeId: String,
    val asOf: LocalDate,
    val serviceYears: Double,
    val pensionFactor: Double,
    val estimatedAnnualPensionNOK: BigDecimal
)
