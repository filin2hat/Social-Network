package classes

import java.time.LocalDate

data class Message(
    val fromId: Int,
    val toId: Int,
    val text: String,
    var isRead: Boolean = false,
    var isEdit: Boolean = false,
    val date: LocalDate = LocalDate.now(),
    val id: Int = 0
)