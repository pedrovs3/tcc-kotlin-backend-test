package com.example.doe_tempo.services

import com.example.doe_tempo.models.User
import com.example.doe_tempo.repository.UserRepository
import com.example.doe_tempo.exceptions.ResourceNotFoundException
import com.example.doe_tempo.utils.encoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UserService {

    @Autowired
    private lateinit var repository: UserRepository

    private val logger = Logger.getLogger(UserService::class.java.name)

    fun findAll(): List<User> {
        logger.info("Finding all people!")
        return repository.findAll()
    }

    fun findById(id: String): User {
        logger.info("Finding one person!")
        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
    }

    fun findByEmail(email: String): User? {
        return repository.findByEmail(email) //?: throw EmailNotFoundException("Email $email not found")
    }

    fun create(user: User) : User{
        logger.info("Creating one person with name ${user.name}!")
        logger.info(user.password)

        return repository.save(user)
    }

    fun update(user: User) : User{
        logger.info("Updating one person with ID ${user.id}!")
        val entity = repository.findById(user.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.name = user.name
        entity.email = user.email
        entity.cpf = user.cpf
        entity.password = user.password
        return repository.save(entity)
    }

    fun delete(id: String) {
        logger.info("Deleting one person with ID $id!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}