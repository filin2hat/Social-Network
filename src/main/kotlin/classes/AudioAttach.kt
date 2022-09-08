package classes

import interfaces.Attachment

data class AudioAttach(val audio: Audio) : Attachment {
    override val type: String = "audio"
}

data class Audio(
    val id: Int,
    val albumId: Int,
    val ownerId: Long,
    val userId: Long
)