package dev.virtanen.springkotlin.album

import jakarta.persistence.*

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