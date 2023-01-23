package dev.virtanen.springkotlin.photo

import dev.virtanen.springkotlin.album.Album
import jakarta.persistence.*


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