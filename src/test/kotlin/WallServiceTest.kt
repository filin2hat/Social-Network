import classes.Comments
import classes.Post
import exceptions.PostNotFoundException
import org.junit.Assert.*
import org.junit.Test
import services.WallService
import services.WallService.createComment
import services.WallService.getAllPosts
import services.WallService.printAll

class WallServiceTest {

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
    fun testPrintAll() {
        printAll()
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