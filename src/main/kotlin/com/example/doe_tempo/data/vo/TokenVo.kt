package com.example.doe_tempo.data.vo

import java.util.Date

data class TokenVo (

    val username: String? = null,
    val authenticated: Boolean? = null,
    val created: Date? = null,
    val expiration: Date? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null,

)