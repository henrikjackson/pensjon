package no.bedrift.pensjon.api.dto

import java.math.BigDecimal

data class EstimatedPensionResponse(
    val employeeId: String,
    val serviceYears: Double,
    val pensionFactor: Double,
    val estimatedAnnualPensionNOK: BigDecimal
)
