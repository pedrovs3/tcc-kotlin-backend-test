package com.example.doe_tempo.controllers

import com.example.doe_tempo.models.Ngo
import com.example.doe_tempo.repository.NgoRepository
import com.example.doe_tempo.services.NgoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ngo")
class NgoController {

    @Autowired
    private lateinit var repository: NgoService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun index(): List<Ngo> {
        return repository.findAll()
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun store(@RequestBody ngo: Ngo): Ngo {
        return repository.create(ngo)
    }

}