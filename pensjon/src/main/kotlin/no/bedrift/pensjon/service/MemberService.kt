package no.bedrift.pensjon.service

import no.bedrift.pensjon.data.Member
import org.springframework.stereotype.Service

@Service
class EmployeeService(private val repo: no.bedrift.pensjon.data.MemberRepository) {
    fun byId(id: String): Member? = repo.findById(id)
}
