package no.bedrift.pensjon.data

interface MemberRepository {
    fun findAll(): List<Member>
    fun findById(id: String): Member?
}