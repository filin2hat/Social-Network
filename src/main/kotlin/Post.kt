import java.time.LocalDate

data class Post(
    val id: Int = 0,
    val likes: Int = 0,
    val ownerID: Int = 0,
    val date: LocalDate = LocalDate.now(),
    val text: String = "text",
    val comment: Comments?
)