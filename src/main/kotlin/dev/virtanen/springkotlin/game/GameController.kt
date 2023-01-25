package dev.virtanen.springkotlin.game

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
@RequestMapping("api/games")
class GameController(val service: GameService) {

    @GetMapping
    fun getAllPhotos() = service.getAll()

    @GetMapping("/{id}")
    fun getPhoto(@PathVariable id: Int) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePhoto(@RequestBody game: Game): Game = service.create(game)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePhoto(@PathVariable id: Int) = service.remove(id)

    @PutMapping("/{id}")
    fun updatePhoto(
        @PathVariable id: Int, @RequestBody game: Game
    ) = service.update(id, game)
}