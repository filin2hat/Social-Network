import classes.*
import services.WallService

val post1 = WallService.add(Post(text = "First post!", comment = null, attach = null))
val post2 = WallService.add(Post(text = "Hello!", comment = null, attach = null))
fun main() {
    WallService.update(
        Post(
            id = 2,
            text = "Second post!",
            comment = Comments(2),
            attach = arrayOf(
                MusicAttach(Music(1, 1, 1, 11)),
                VideoAttach(Video(2, 2, 2, 22))
            )
        )
    )
    WallService.createComment(1, Comments())
    WallService.addReport(1, 0, Report(reason = 1))
    WallService.delete(post1)
    WallService.getAllPosts()
    WallService.print(post2)
}