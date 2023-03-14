package com.example.doe_tempo.services

import com.example.doe_tempo.exceptions.ResourceNotFoundException
import com.example.doe_tempo.models.Ngo
import com.example.doe_tempo.repository.NgoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class NgoService {

    @Autowired
    private lateinit var repository: NgoRepository

    private val logger = Logger.getLogger(NgoService::class.java.name)

    fun findAll(): List<Ngo> {
        logger.info("Find all NGO`s!")

        return repository.findAll()
    }

    fun findById(id: String): Ngo {
        logger.info("Finding one person!")
        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
    }

    fun create(ngo: Ngo) : Ngo{
        logger.info("Creating one NGO with name ${ngo.name}!")

        return repository.save(ngo)
    }

    fun update(ngo: Ngo) : Ngo{
        logger.info("Updating one NGO with ID ${ngo.id}!")
        val entity = repository.findById(ngo.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.name = ngo.name
        entity.email = ngo.email
        entity.cnpj = ngo.cnpj
        entity.password = ngo.password
        return repository.save(entity)
    }

    fun delete(id: String) {
        logger.info("Deleting one NGO with ID $id!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}