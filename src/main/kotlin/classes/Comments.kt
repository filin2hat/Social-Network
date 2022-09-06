package classes

import interfaces.Attachment
import java.time.LocalDate

data class Comments(
    val id: Int = 0,
    val ownerId: Int = 0,
    val text: String = "simple text",
    val count: Int = 0,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canOpen: Boolean = true,
    val canClose: Boolean = false,
    val date: LocalDate = LocalDate.now(),
    var isDelete: Boolean = false,
    val attachments: MutableList<Attachment> = mutableListOf()
)