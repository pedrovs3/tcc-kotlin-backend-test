package com.example.doe_tempo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class DoeTempoApplication

fun main(args: Array<String>) {
    runApplication<DoeTempoApplication>(*args)
}
