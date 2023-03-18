package com.example.doe_tempo.repository

import com.example.doe_tempo.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, String?> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    fun findByEmail(@Param("email") email: String): User?

    @Query("SELECT u FROM User u WHERE u.name = :userName")
    fun findByUsername(@Param("userName") name: String?): User?
}