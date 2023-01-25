package dev.virtanen.springkotlin.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(val repository: UserRepository) {

    fun getAll(): List<User> = repository.findAll()

    fun getById(id: Int): User = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(user: User): User = repository.save(user)

    fun remove(id: Int) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: Int, user: User): User {
        return if (repository.existsById(id)) {
            repository.save(user)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}