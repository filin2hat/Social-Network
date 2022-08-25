val defComment = Comments()
fun main() {
    WallService.add(Post(text = "First post!", comment = defComment))
    WallService.add(Post(text = "Hello!", comment = defComment))
    WallService.update(Post(id = 2, text = "Second post!", comment = defComment))
    WallService.getAllPosts()

}