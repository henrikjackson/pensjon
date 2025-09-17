package no.bedrift.pensjon.service

import no.bedrift.pensjon.api.dto.EstimatedPensionResponse
import no.bedrift.pensjon.data.Member
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.Period
import kotlin.math.max
import kotlin.math.min

@Service
class PensionEstimationService {
    fun estimate(emp: Member, asOf: LocalDate): EstimatedPensionResponse {
        // Hele år fra startdato til asOf
        val wholeYears = if (!asOf.isBefore(emp.employmentStart))
            Period.between(emp.employmentStart, asOf).years
        else 0

        // serviceYears = min(hele år * (positionPercent/100), 30)
        val serviceYears = max(min(wholeYears.toDouble() * (emp.positionPercent / 100.0), 30.0), 0.0)

        // pensionFactor = serviceYears / 30
        val pensionFactor = serviceYears / 30.0

        // estimatedAnnualPension = annualSalaryNOK * 0.66 * pensionFactor
        val estimated = BigDecimal(emp.annualSalaryNOK)
            .multiply(BigDecimal("0.66"))
            .multiply(BigDecimal.valueOf(pensionFactor))
            .setScale(2, RoundingMode.HALF_UP)

        return EstimatedPensionResponse(
            employeeId = emp.id,
            asOf = asOf,
            serviceYears = serviceYears,
            pensionFactor = pensionFactor,
            estimatedAnnualPensionNOK = estimated
        )
    }
}