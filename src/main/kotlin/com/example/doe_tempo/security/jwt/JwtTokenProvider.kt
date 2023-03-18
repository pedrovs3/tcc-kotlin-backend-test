package com.example.doe_tempo.security.jwt

import com.auth0.jwt.algorithms.Algorithm
import com.example.doe_tempo.data.vo.TokenVo
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.Base64
import java.util.Date

@Service
class JwtTokenProvider {

    @Value("\${security.jwt.token.secret-key: secret}")
    private var secretKey = "teste"

    @Value("\${security.jwt.token.secret-key: 3600000}")
    private var validityInMilliseconds: Long = 3_600_000 // 1 Hour

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    private lateinit var algorithm: Algorithm

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        algorithm = Algorithm.HMAC256(secretKey.toByteArray())
    }

    fun createAccessToken(email: String, password: String): TokenVo {
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        val accessToken = getAccessToken(email, password, now, validity)
        val refreshToken = getRefreshToken(email, password, now)

        return TokenVo(
            email,
            authenticated = true,
            accessToken = accessToken,
            refreshToken = refreshToken,
            created = now,
            expiration = validity
        )
    }

    private fun getRefreshToken(email: String, password: String, now: Date): Any {

    }

    private fun getAccessToken(email: String, password: String, now: Date, validity: Date): Any {

    }
}