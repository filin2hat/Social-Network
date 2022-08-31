package classes

data class Audio(
    val id: Int,
    val albumId: Int,
    val ownerId: Long,
    val userId: Long
)