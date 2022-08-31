import classes.*
import services.*

val defComment = Comments()
fun main() {
    WallService.add(Post(text = "First post!", comment = null))
    WallService.add(Post(text = "Hello!", comment = null))
    WallService.update(Post(id = 2, text = "Second post!", comment = defComment))
    WallService.getAllPosts()

}