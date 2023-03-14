package com.example.doe_tempo.models

import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "tbl_user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = "",

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, length = 256)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false, length = 14)
    var cpf: String,

    @ManyToOne
    @JoinColumn(name = "id_gender", referencedColumnName = "id")
    var gender: Gender,

    @ManyToMany(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
    @JoinTable(
        name = "tbl_user_address",
        joinColumns = [JoinColumn(name = "id_user", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "id_address", referencedColumnName = "id")],
    )
    var address: List<Address>?,

    @Column(nullable = false)
    var birthdate: Date,

    @Column(nullable = true)
    var rg: String?,
)
