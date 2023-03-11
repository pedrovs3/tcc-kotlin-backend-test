package com.example.doe_tempo.Models

import jakarta.persistence.*
import org.ietf.jgss.GSSName
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

    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "tbl_user_address",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_address")]
    )
    var address: List<Address>,

    @Column(nullable = false)
    var birthdate: Date,

    @Column(nullable = true)
    var rg: String?,
)
