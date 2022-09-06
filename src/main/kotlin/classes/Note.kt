package classes

import java.time.LocalDate

data class Note(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "title",
    val text: String = "text",
    val privacy: Boolean = true,
    val commentPrivacy: Boolean = true,
    val date: LocalDate = LocalDate.now(),
    var isDelete: Boolean = false,
    val comments: MutableList<Comments> = mutableListOf()
)