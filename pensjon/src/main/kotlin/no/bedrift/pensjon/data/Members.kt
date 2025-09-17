package no.bedrift.pensjon.data

import java.time.LocalDate

data class Member(
    val id: String,
    val name: String,
    val birthYear: Int,
    val annualSalaryNOK: Long,
    val positionPercent: Int,
    val employmentStart: LocalDate
)