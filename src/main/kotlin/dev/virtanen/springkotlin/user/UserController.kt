package dev.virtanen.springkotlin.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
@RequestMapping("api/users")
class UserController(val service: UserService) {

    @GetMapping
    fun getAllUsers() = service.getAll()

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveUser(@RequestBody user: User): User = service.create(user)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Int) = service.remove(id)

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Int, @RequestBody user: User
    ) = service.update(id, user)
}