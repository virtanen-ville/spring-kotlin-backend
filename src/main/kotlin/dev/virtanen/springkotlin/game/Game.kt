package dev.virtanen.springkotlin.game

import dev.virtanen.springkotlin.user.User
import jakarta.persistence.*


@Entity
@Table(name = "games", schema = "public")
class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @Column(nullable = false)
    val round: Int,
    @Column(nullable = false)
    val isActive: Boolean,
    @ManyToOne(fetch = FetchType.LAZY) //(cascade = [CascadeType.ALL])
    @JoinColumn(

        nullable = false
    ) // name is the column name in the photo table, if omitted the property name and album table column name would be joined with underscore
    val user: User, // property could be named as album, and in album table column name would be id (default)
    @Column(nullable = false)
    val amountWon: Int = 0,
)