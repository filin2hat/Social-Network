package classes

data class Picture(
    val id: Int,
    val albumId: Int,
    val ownerId: Long,
    val userId: Long
)