package no.bedrift.pensjon.api

import no.bedrift.pensjon.api.dto.EstimatedPensionResponse
import no.bedrift.pensjon.data.Member
import no.bedrift.pensjon.service.EmployeeService
import no.bedrift.pensjon.service.PensionEstimationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/pensjon/brukere")
class EmployeeController(
    private val employeeService: EmployeeService,
    private val pensionEstimationService: PensionEstimationService,
) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<Member> =
        employeeService.byId(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @GetMapping("/estimat/{id}")
    fun estimateAnnual(
        @PathVariable id: String
    ): ResponseEntity<EstimatedPensionResponse> {
        val employee = employeeService.byId(id) ?: return ResponseEntity.notFound().build()
        val response = pensionEstimationService.estimate(employee, LocalDate.now())
        return ResponseEntity.ok(response)
    }
}
