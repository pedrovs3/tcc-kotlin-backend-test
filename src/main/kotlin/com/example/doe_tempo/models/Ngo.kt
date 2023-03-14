package com.example.doe_tempo.models

import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "tbl_ngo")
data class Ngo(

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = "",

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var cnpj: String,

    @Column(nullable = false, length = 256)
    var email: String,

    @Column(name = "foundation_date", nullable = true)
    var foundationDate: Date?,

    @Column(nullable = true)
    var description: String?,

    @ManyToMany(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
    @JoinTable(
        name = "tbl_ngo_address",
        joinColumns = [JoinColumn(name = "id_ngo", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "id_address", referencedColumnName = "id")],
    )
    var address: List<Address>?,

    @Column(nullable = false)
    var password: String
)
