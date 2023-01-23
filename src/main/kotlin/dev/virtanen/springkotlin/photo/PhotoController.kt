package dev.virtanen.springkotlin.photo

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
@RequestMapping("api/photos")
class PhotoController(val service: PhotoService) {

    @GetMapping
    fun getAllPhotos() = service.getAll()

    @GetMapping("/{id}")
    fun getPhoto(@PathVariable id: Int) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePhoto(@RequestBody photo: Photo): Photo = service.create(photo)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePhoto(@PathVariable id: Int) = service.remove(id)

    @PutMapping("/{id}")
    fun updatePhoto(
        @PathVariable id: Int, @RequestBody photo: Photo
    ) = service.update(id, photo)
}