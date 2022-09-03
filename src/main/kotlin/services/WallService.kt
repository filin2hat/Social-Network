package services

import classes.Post

object WallService {
    private var posts = emptyArray<Post>()
    private var lastID = 0

    fun add(post: Post): Post {
        posts += post.copy(id = setID())
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, target) in posts.withIndex()) {
            if (target.id == post.id) {
                posts[index] = post.copy(id = target.id, ownerID = target.ownerID, date = target.date)
                return true
            }
        }
        return false
    }

    private fun setID(): Int {
        lastID += 1
        return lastID
    }

    fun getAllPosts() {
        for (post in posts) {
            val (id, _, _, _, text, _, attach) = post
            println(
                """
            ID = $id
            TEXT = $text
            ATTACHMENT = $attach
            """.trimIndent()
            )
        }
    }

    fun printAll() {
        for (post in posts) {
            println(post)
        }
    }
}