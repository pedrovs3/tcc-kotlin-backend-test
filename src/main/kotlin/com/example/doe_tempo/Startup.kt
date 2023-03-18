package com.example.doe_tempo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

@SpringBootApplication
class DoeTempoApplication

fun main(args: Array<String>) {
    runApplication<DoeTempoApplication>(*args)

    val encoders: MutableMap<String, PasswordEncoder> = HashMap()
    val pbkdf2Encoder = Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256)
    encoders["pbkdf2"] = pbkdf2Encoder
    val passwordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
    passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder)

    val result = passwordEncoder.encode("foo-bar")
    println("My hash $result")
}
