package com.example.doe_tempo.repository

import com.example.doe_tempo.models.Ngo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NgoRepository: JpaRepository<Ngo, String?> {

}