package classes

import interfaces.Attachment

data class VideoAttach(val video: Video) : Attachment {
    override val type: String = "video"
}

data class Video(
    val id: Int,
    val albumId: Int,
    val ownerId: Long,
    val userId: Long
)