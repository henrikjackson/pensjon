package no.bedrift.pensjon.data

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Repository

@Repository
class InMemoryEmployeeRepository(
    private val objectMapper: ObjectMapper,
    @Value("classpath:data/members.json") private val dataFile: Resource
) : MemberRepository {

    private val all: List<Member>
    private val byId: Map<String, Member>

    init {
        dataFile.inputStream.use { input ->
            val list: List<Member> = objectMapper.readValue(input, object : com.fasterxml.jackson.core.type.TypeReference<List<Member>>() {})
            all = list.sortedBy { it.id }
            byId = all.associateBy { it.id }
        }
    }

    override fun findAll(): List<Member> = all

    override fun findById(id: String): Member? = byId[id]
}
