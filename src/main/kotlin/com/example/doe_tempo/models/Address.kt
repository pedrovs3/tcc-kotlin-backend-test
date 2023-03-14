package com.example.doe_tempo.models

import jakarta.persistence.*

@Entity
@Table(name = "tbl_address")
data class Address(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    var id: String?,

    @Column(name = "postal_code")
    var postalCode: String,

    @Column(name = "number")
    var number: String,

    @Column(nullable = true)
    var complement: String?,

//    @ManyToMany(mappedBy = "address", fetch = FetchType.LAZY)
//    private var users: List<User>?
)
