package classes

data class Comments(
    val id: Int = 0,
    val count: Int = 0,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canOpen: Boolean = true,
    val canClose: Boolean = false
)