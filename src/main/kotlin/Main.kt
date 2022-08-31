import classes.Comments
import classes.Music
import classes.MusicAttach
import classes.Post
import services.WallService

fun main() {
    WallService.add(Post(text = "First post!", comment = null, attach = null))
    WallService.add(Post(text = "Hello!", comment = Comments(1), attach = null))
    WallService.update(Post(id = 2, text = "Second post!", comment = Comments(2, true, true, false), attach = null))
    val postWithAttachment =
        Post(text = "add music attach", attach = arrayOf(MusicAttach(Music(1, 1, 1, 11))), comment = null)
    WallService.add(postWithAttachment)
    WallService.getAllPosts()
    println()
    WallService.printAll()

}