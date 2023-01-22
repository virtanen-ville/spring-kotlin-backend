package dev.virtanen.springkotlin

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import org.springframework.data.annotation.Id

import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@SpringBootApplication
class SpringKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringKotlinApplication>(*args)
}

@RestController
class MessageResource {

    // Create some mock data
    val messages = listOf(
        Message("1", "Hello, World!"),
        Message("2", "Hello, Kotlin!"),
        Message("3", "Hello, Spring Boot!"),
        Message("4", "Hello, Spring!"),
        Message("5", "Hello, Angular!"),
        Message("6", "Hello, Java!"),
        Message("7", "Hello, React!"),
        Message("8", "Hello, Vue!"),
        Message("9", "Hello, TypeScript!"),
        Message("10", "Hello, JavaScript!"),
        Message("11", "Hello, Python!"),
        Message("12", "Hello, C#!"),
        Message("13", "Hello, NODEJS!"),
    )

    @GetMapping("/")
    fun index(): List<Message> = messages

    // New route for the message resource class that returns a single message object with the id and text properties set.
    @GetMapping("/message")
    fun message(): Message = Message("1", "Hello!")

    // Return a parameter from the request URL.
    @GetMapping("/message/{id}")
    fun message(@PathVariable id: String): Message = messages.find { it.id == id } ?: Message("1", "Hello!")

    @GetMapping("/hello")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name: String?): String? {
        return String.format("Hello %s!", name)
    }

    @GetMapping("/hello2")
    fun hello2(@RequestParam(value = "name", defaultValue = "World") name: String?): String? = "Hello ${name}!"


}

data class Message(val id: String?, val text: String)


@Entity
@Table(name = "albums", schema = "public")
class Album(
    @Column(name = "album_name", nullable = false)
    val albumName: String,
    @Column(name = "album_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val albumId: Int? = null,
)

@Entity
@Table(name = "photos", schema = "public")
class Photo(
    @Column(name = "photo_url", nullable = false)
    val url: String,
    @Column(nullable = false)
    val photoCaption: String,
    @ManyToOne(fetch = FetchType.LAZY) //(cascade = [CascadeType.ALL])
    @JoinColumn(
        name = "album_id",
        nullable = true
    ) // name is the column name in the photo table, if omitted the property name and album table column name would be joined with underscore
    val albumId: Album? = null, // property could be named as album, and in album table column name would be id (default)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var photoId: Int? = null,
)

@Repository
interface PhotoRepository : JpaRepository<Photo, Int>

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

@RequestMapping("api/photos")
@RestController
class PlayerController(val service: PhotoService) {

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