package dev.virtanen.springkotlin.game

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GameService(val repository: GameRepository) {

    fun getAll(): List<Game> = repository.findAll()

    fun getById(id: Int): Game = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(game: Game): Game = repository.save(game)

    fun remove(id: Int) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: Int, game: Game): Game {
        return if (repository.existsById(id)) {
            game.id = id
            repository.save(game)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}