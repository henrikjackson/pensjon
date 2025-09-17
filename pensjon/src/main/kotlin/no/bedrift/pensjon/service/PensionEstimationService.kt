package no.bedrift.pensjon.service

import no.bedrift.pensjon.api.dto.EstimatedPensionResponse
import no.bedrift.pensjon.data.Member
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.Period
import kotlin.math.min

@Service
class PensionEstimationService {
    fun estimate(member: Member, today: LocalDate): EstimatedPensionResponse {
        // Hele år fra startdato til today
        val wholeYears = if (!today.isBefore(member.employmentStart))
            Period.between(member.employmentStart, today).years
        else 0

        // serviceYears = min(hele år * (positionPercent/100), 30)
        val serviceYears = min(wholeYears.toDouble() * (member.positionPercent / 100.0), 30.0)

        // pensionFactor = serviceYears / 30
        val pensionFactor = serviceYears / 30.0

        // estimatedAnnualPension = annualSalaryNOK * 0.66 * pensionFactor
        val estimatedAnnualPensionNOK = BigDecimal(member.annualSalaryNOK)
            .multiply(BigDecimal("0.66"))
            .multiply(BigDecimal.valueOf(pensionFactor))
            .setScale(2, RoundingMode.HALF_UP)

        return EstimatedPensionResponse(
            employeeId = member.id,
            serviceYears = serviceYears,
            pensionFactor = pensionFactor,
            estimatedAnnualPensionNOK = estimatedAnnualPensionNOK
        )
    }
}