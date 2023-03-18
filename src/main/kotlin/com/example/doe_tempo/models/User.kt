package com.example.doe_tempo.models

import com.example.doe_tempo.utils.encoder
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.sql.Date

@Entity
@Table(name = "tbl_user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = "",

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, length = 256, unique = true)
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
): UserDetails {
    fun comparePassword(password: String): Boolean {
        return encoder.matches(password, this.password)
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }
}
