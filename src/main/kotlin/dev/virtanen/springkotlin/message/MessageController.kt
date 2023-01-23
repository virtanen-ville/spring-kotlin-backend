package dev.virtanen.springkotlin.message

import dev.virtanen.springkotlin.message.Message
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class MessageController {

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