package com.example.doe_tempo.repository

import com.example.doe_tempo.models.Gender
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GenderRepository: JpaRepository<Gender, String> {
}