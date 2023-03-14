package com.example.doe_tempo.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tbl_gender")
data class Gender(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String,

    @Column(nullable = true)
    var name: String?,

    @Column(nullable = true)
    var abbreviation: String?,
)
