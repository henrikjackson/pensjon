package no.bedrift.pensjon.service

import no.bedrift.pensjon.data.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate


class PensionEstimationServiceTest {

    private val estimator = PensionEstimationService()

    private fun testMember(
        id: String = "e1",
        name: String = "Test",
        birthYear: Int = 1980,
        annualSalaryNOK: Long = 600_000,
        positionPercent: Int = 100,
        employmentStart: LocalDate = LocalDate.of(2015, 1, 1)
    ) = Member(
        id = id,
        name = name,
        birthYear = birthYear,
        annualSalaryNOK = annualSalaryNOK,
        positionPercent = positionPercent,
        employmentStart = employmentStart
    )

    @Test
    fun `full-time 10 whole years gives factor 10_30 and correct estimate`() {
        val asOf = LocalDate.of(2025, 1, 1)      // 10 hele år fra 2015-01-01
        val e = testMember(annualSalaryNOK = 600_000, positionPercent = 100, employmentStart = LocalDate.of(2015, 1, 1))

        val resp = estimator.estimate(e, asOf)

        assertThat(resp.serviceYears).isEqualTo(10.0)
        assertThat(resp.pensionFactor).isEqualTo(10.0 / 30.0)
        // 600_000 * 0.66 * (10/30) = 132_000.00
        assertThat(resp.estimatedAnnualPensionNOK).isEqualTo(BigDecimal("132000.00"))
    }

    @Test
    fun `part-time 60 percent over 5 years yields fractional serviceYears and estimate`() {
        val asOf = LocalDate.of(2025, 1, 1)      // 5 hele år fra 2020-01-01
        val e = testMember(
            annualSalaryNOK = 560_000,
            positionPercent = 60,
            employmentStart = LocalDate.of(2020, 1, 1)
        )

        val resp = estimator.estimate(e, asOf)

        assertThat(resp.serviceYears).isEqualTo(3.0)            // 5 * 0.60
        assertThat(resp.pensionFactor).isEqualTo(3.0 / 30.0)    // 0.1
        // 560_000 * 0.66 * 0.1 = 36_960.00
        assertThat(resp.estimatedAnnualPensionNOK).isEqualTo(BigDecimal("36960.00"))
    }

    @Test
    fun `cap at 30 service years yields factor 1, estimate equals 66 percent of salary`() {
        val asOf = LocalDate.of(2025, 1, 1)      // > 30 hele år fra 1970-01-01
        val e = testMember(
            annualSalaryNOK = 900_000,
            positionPercent = 100,
            employmentStart = LocalDate.of(1970, 1, 1)
        )

        val resp = estimator.estimate(e, asOf)

        assertThat(resp.serviceYears).isEqualTo(30.0)
        assertThat(resp.pensionFactor).isEqualTo(1.0)
        // 900_000 * 0.66 = 594_000.00
        assertThat(resp.estimatedAnnualPensionNOK).isEqualTo(BigDecimal("594000.00"))
    }
}
