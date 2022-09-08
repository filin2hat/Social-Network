import classes.Comments
import classes.Post
import classes.Report
import exceptions.CommentNotFoundException
import exceptions.PostNotFoundException
import exceptions.UnknownReasonException
import org.junit.Assert.*
import org.junit.Test
import services.WallService
import services.WallService.createComment
import services.WallService.getAllPosts


class WallServiceTest {

    @Test
    fun reportShouldNotThrow() {
        val post = WallService.add(Post(attach = null))
        val comment = Comments()
        createComment(post.id, comment)
        val report = Report(1, comment.id, 1)
        WallService.addReport(1, comment.id, report)
    }

    @Test(expected = UnknownReasonException::class)
    fun reportUnknownReasonException() {
        val post = WallService.add(Post(attach = null))
        val comment = Comments()
        createComment(post.id, comment)
        val report = Report(1, comment.id, -1)
        WallService.addReport(1, comment.id, report)
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportCommentNotFoundException() {
        val post = WallService.add(Post(attach = null))
        val comment = Comments()
        createComment(post.id, comment)
        val report = Report(1, comment.id, 1)
        WallService.addReport(1, comment.id - 1, report)
    }

    @Test(expected = PostNotFoundException::class)
    fun reportPostNotFoundException() {
        val post = WallService.add(Post(attach = null))
        val comment = Comments()
        createComment(post.id, comment)
        val report = Report(1, comment.id, 1)
        WallService.addReport(1 - 1, comment.id, report)
    }

    @Test(expected = PostNotFoundException::class)
    fun commentShouldThrow() {
        val post = WallService.add(Post(attach = null))
        val falseId = post.id + 1
        createComment(falseId, Comments())
    }

    @Test()
    fun commentShouldNotThrow() {
        val comment = Comments()
        val returnComment = createComment(1, Comments())
        assertEquals(comment, returnComment)
    }

    @Test
    fun addPost() {
        val post = WallService.add(Post(attach = null))
        assertNotEquals(0, post.id)
    }

    @Test
    fun updatePost() {
        val post = WallService.add(Post(attach = null))
        val exist = WallService.update(post.copy(text = "test text"))
        assertTrue(exist)
    }

    @Test
    fun notUpdatePost() {
        val post = WallService.add(Post(attach = null))
        val exist = WallService.update(post.copy(text = "test text", id = post.id + 1))
        assertFalse(exist)
    }

    @Test
    fun testGetAllPosts() {
        getAllPosts()
    }

    @Test
    fun testMain() {
        main()
    }
}