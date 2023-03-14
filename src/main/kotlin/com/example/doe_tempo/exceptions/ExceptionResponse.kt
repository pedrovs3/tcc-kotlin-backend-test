package com.example.doe_tempo.exceptions

import java.time.LocalDate
import java.util.*

class ExceptionResponse (
    val timeStamp: Date = Date(),
    val message: String?,
    val details: String
)