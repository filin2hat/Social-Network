package classes

import interfaces.Attachment

data class PictureAttach(val picture: Picture) : Attachment {
    override val type: String = "picture"
}

data class Picture(
    val id: Int,
    val albumId: Int,
    val ownerId: Long,
    val userId: Long
)