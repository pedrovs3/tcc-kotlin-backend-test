package com.example.doe_tempo.controllers

import com.example.doe_tempo.constants.SecurityConstants
import com.example.doe_tempo.dto.LoginDTO
import com.example.doe_tempo.exceptions.ExceptionResponse
import com.example.doe_tempo.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private lateinit var repository: UserRepository

    @PostMapping(
        value = ["/login"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun login(@RequestBody body: LoginDTO): ResponseEntity<Any> {
        val user = repository.findByEmail(body.email) ?:
            return ResponseEntity
                .badRequest()
                .body(
                    ExceptionResponse(message = "User not found!", details = "")
                )

        if (!user.comparePassword(body.password)){
            return ResponseEntity
                .badRequest()
                .body(
                    ExceptionResponse(message = "Invalid data!", details = "Invalid password.")
                )
        }

        val issuer = user.id

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(
                Date(System.currentTimeMillis() + 60 * 24 * 1000) // 1 day
            ).signWith(SignatureAlgorithm.HS512, "secret").compact()

        return ResponseEntity.ok(jwt)
    }
}