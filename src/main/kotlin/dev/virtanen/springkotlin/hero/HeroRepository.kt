package dev.virtanen.springkotlin.hero;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HeroRepository : JpaRepository<Hero, Int> {
}