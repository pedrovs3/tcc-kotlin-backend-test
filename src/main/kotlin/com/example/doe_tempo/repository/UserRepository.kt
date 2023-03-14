package com.example.doe_tempo.repository

import com.example.doe_tempo.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, String?> {
    fun findByEmail(email: String): User?
}