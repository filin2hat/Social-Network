package classes

data class User(
    val name: String,
    val surname: String,
    var age: Int,
    val id: Int = 0,
    var isDeleted: Boolean = false
)