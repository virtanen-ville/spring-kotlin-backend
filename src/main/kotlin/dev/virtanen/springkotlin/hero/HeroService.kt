package dev.virtanen.springkotlin.hero

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class HeroService(val heroes: HeroRepository) {

    fun getAll(): List<Hero> = heroes.findAll()

    fun createMany(heroList: List<Hero>): List<Hero> = heroes.saveAll(heroList)

    fun getById(id: Int): Hero = heroes.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(hero: Hero): Hero = heroes.save(hero)

    fun remove(id: Int) {
        if (heroes.existsById(id)) heroes.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: Int, hero: Hero): Hero {
        return if (heroes.existsById(id)) {
            hero.id = id
            heroes.save(hero)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}