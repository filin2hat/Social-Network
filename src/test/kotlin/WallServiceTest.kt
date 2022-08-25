import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @org.junit.Test
    fun addPost() {
        val post = WallService.add(Post(comment = defComment))
        assertNotEquals(0, post.id)
    }

    @org.junit.Test
    fun updatePost() {
        val post = WallService.add(Post(comment = defComment))
        val exist = WallService.update(post.copy(text = "test text"))
        assertTrue(exist)
    }

    @org.junit.Test
    fun notUpdatePost() {
        val post = WallService.add(Post(comment = defComment))
        val exist = WallService.update(post.copy(text = "test text", id = post.id + 1))
        assertFalse(exist)
    }

    @org.junit.Test
    fun getAllPosts() {

    }
}