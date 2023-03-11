package com.example.doe_tempo.Models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
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
