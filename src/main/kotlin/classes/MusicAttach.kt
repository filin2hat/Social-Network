package classes

import interfaces.Attachment

data class MusicAttach(val music: Music) : Attachment {
    override val type: String = "music"
}

data class Music(
    val id: Int,
    val albumId: Int,
    val ownerId: Long,
    val userId: Long
)
