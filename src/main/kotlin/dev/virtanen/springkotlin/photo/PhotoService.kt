package dev.virtanen.springkotlin.photo

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PhotoService(val photos: PhotoRepository) {

    fun getAll(): List<Photo> = photos.findAll()

    fun getById(id: Int): Photo = photos.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(photo: Photo): Photo = photos.save(photo)

    fun remove(id: Int) {
        if (photos.existsById(id)) photos.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: Int, photo: Photo): Photo {
        return if (photos.existsById(id)) {
            photo.photoId = id
            photos.save(photo)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}