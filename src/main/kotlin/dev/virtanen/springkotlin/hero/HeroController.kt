package dev.virtanen.springkotlin.hero

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
@RequestMapping("api/heroes")
class HeroController(val service: HeroService) {

    @GetMapping
    fun getAllHeroes() = service.getAll()

    @GetMapping("/{id}")
    fun getHero(@PathVariable id: Int) = service.getById(id)

    @GetMapping("/")
    // Get a hero by request query string variable name
    fun getHeroByName(@RequestParam name: String) = service.getAll().filter { it.name.contains(name, true) }
    //"Hello, $name!"

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveHero(@RequestBody hero: Hero): Hero = service.create(hero)

    @PostMapping("/many")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveHeroes(@RequestBody heroes: List<Hero>): List<Hero> = service.createMany(heroes)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteHero(@PathVariable id: Int) = service.remove(id)

    @PutMapping("/{id}")
    fun updateHero(
        @PathVariable id: Int, @RequestBody hero: Hero
    ) = service.update(id, hero)
}