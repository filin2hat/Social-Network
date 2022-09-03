import classes.*
import services.WallService

fun main() {
    WallService.add(Post(text = "First post!", comment = null, attach = null))
    WallService.add(Post(text = "Hello!", comment = Comments(1), attach = null))
    WallService.update(Post(id = 2, text = "Second post!", comment = Comments(2), attach = null))
    val postWithAttachment =
        Post(
            text = "add music attach",
            attach = arrayOf(
                MusicAttach(Music(1, 1, 1, 11)),
                VideoAttach(Video(2, 2, 2, 22))
            ), comment = Comments(3)
        )
    WallService.add(postWithAttachment)
    WallService.getAllPosts()
    println()
    WallService.printAll()

}