package classes

import java.time.LocalDate

data class Chat(
    val userId1: Int,
    val userId2: Int,
    val id: Int = 0,
    val date: LocalDate = LocalDate.now(),
    val messages: MutableList<Message> = mutableListOf()
)