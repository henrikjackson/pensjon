package no.bedrift.pensjon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PensjonApiApplication

fun main(args: Array<String>) {
    runApplication<PensjonApiApplication>(*args)
}
