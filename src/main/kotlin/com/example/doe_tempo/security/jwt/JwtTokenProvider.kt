package com.example.doe_tempo.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.doe_tempo.data.vo.TokenVo
import com.example.doe_tempo.exceptions.InvalidJwtAuthenticationException
import com.example.doe_tempo.repository.UserRepository
import jakarta.annotation.PostConstruct
import jakarta.servlet.Servlet
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.Base64
import java.util.Date

@Service
class JwtTokenProvider(private val userRepository: UserRepository) {

    @Value("\${security.jwt.token.secret-key: secret}")
    private var secretKey = "teste"

    @Value("\${security.jwt.token.expire-length: 3600000}")
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
        val user = userRepository.findByEmail(email)

        if (user != null) {
            val accessToken = getAccessToken(user.name, user.id, now, validity)
            val refreshToken = getRefreshToken(user.name, user.id, now)
            if (!user.comparePassword(password)){
                return TokenVo(
                    email,
                    authenticated = true,
                    accessToken = accessToken,
                    refreshToken = refreshToken,
                    created = now,
                    expiration = validity
                )
            }
        }

        return TokenVo(
            email,
            authenticated = false,
            accessToken = null,
            refreshToken = null,
            created = null,
            expiration = null
        )
    }

    private fun getRefreshToken(name: String, id: String, now: Date): String {
        val validityRefreshToken = Date(now.time + validityInMilliseconds * 3) // Renovado por 3 horas

        return JWT.create()
            .withClaim("id", id)
            .withExpiresAt(validityRefreshToken)
            .withSubject(name)
            .sign(algorithm)
            .trim()
    }

    private fun getAccessToken(name: String, id: String, now: Date, validity: Date): String {
        val issuerUrl: String = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()

        return JWT.create()
            .withClaim("id", id)
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .withSubject(name)
            .withIssuer(issuerUrl)
            .sign(algorithm)
            .trim()
    }

    fun getAuthentication(token: String): Authentication {
        val decodedJWT: DecodedJWT = decodedToken(token)
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(decodedJWT.subject)
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun decodedToken(token: String): DecodedJWT {
        val algorithm = Algorithm.HMAC256(secretKey.toByteArray())
        val verifier: JWTVerifier = JWT.require(algorithm).build()
        return verifier.verify(token)
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (!bearerToken.isNullOrBlank() && bearerToken.startsWith("Bearer ")){
            bearerToken.substring("Bearer ".length)
        } else {
            return null
        }
    }

    fun validateToken(token: String): Boolean {
        val decodedJWT = decodedToken(token)
        try {
            if(decodedJWT.expiresAt.before(Date())) return false
            return true
        } catch (e: Exception) {
            throw InvalidJwtAuthenticationException("Expired or Invalid JWT token!")
        }
    }
}