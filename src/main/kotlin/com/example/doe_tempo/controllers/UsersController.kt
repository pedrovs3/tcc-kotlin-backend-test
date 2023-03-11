package com.example.doe_tempo.controllers

import com.example.doe_tempo.Models.User
import com.example.doe_tempo.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UsersController {

    @Autowired
    private lateinit var repository: UserService

    @GetMapping(
        value = ["/test"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun index(): List<User> {
        return repository.findAll()
    }

    @GetMapping(
        value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun show(@PathVariable(value = "id") id: String): User {
        return repository.findById(id)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun store(@RequestBody user: User): User {
        return repository.create(user)
    }

    @PutMapping(
        value = ["/{id}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun update(@PathVariable("id") id: String, @RequestBody user: User): User {
        user.id = id
        return repository.update(user)
    }
}