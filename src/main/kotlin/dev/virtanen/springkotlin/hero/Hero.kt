package dev.virtanen.springkotlin.hero

import jakarta.persistence.*


@Entity
@Table(name = "heroes", schema = "public")
class Hero(
    @Column(name = "name", nullable = false)
    val name: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
)