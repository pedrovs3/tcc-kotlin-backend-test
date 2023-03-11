package com.example.doe_tempo.Models

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

//    @ManyToMany(mappedBy = "address", cascade = [CascadeType.PERSIST])
//    var users: List<User>?
)
