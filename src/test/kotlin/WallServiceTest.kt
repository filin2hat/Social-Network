import classes.Post
import org.junit.Assert.*
import org.junit.Test
import services.WallService

class WallServiceTest {

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
    fun testMain() {
        main()
    }
}