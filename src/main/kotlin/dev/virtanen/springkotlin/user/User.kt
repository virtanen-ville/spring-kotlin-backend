package dev.virtanen.springkotlin.user

import jakarta.persistence.*

@Entity
@Table(name = "albums", schema = "public")
class User(
    @Column(nullable = false)
    val username: String,
    @Column(nullable = false)
    val password: String,
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
)