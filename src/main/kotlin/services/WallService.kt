package services

import classes.Comments
import classes.Post
import classes.Report
import exceptions.CommentNotFoundException
import exceptions.PostNotFoundException
import exceptions.UnknownReasonException
import interfaces.CrudService

object WallService : CrudService<Post> {
    private val posts = mutableListOf<Post>()
    private val comments = mutableListOf<Comments>()
    private val reports = mutableListOf<Report>()
    private var lastID = 0

    fun addReport(postId: Int, commentId: Int, report: Report): Report {
        if (report.reason!! < 0 || report.reason > 6) throw UnknownReasonException()
        for (post in posts) {
            if (postId == post.id) {
                for (comment in comments) {
                    if (commentId == comment.id) {
                        reports += report
                        return reports.last()
                    }
                }
                throw CommentNotFoundException()
            }
        }
        throw PostNotFoundException()
    }

    fun createComment(postId: Int, comment: Comments): Comments {
        for (post in posts) {
            if (postId == post.id) {
                comments += comment
                return comments.last()
            }
        }
        throw PostNotFoundException()
    }

    override fun add(post: Post?): Post {
        if (post != null) {
            posts += post.copy(id = getId())
            return posts.last()
        }
        throw PostNotFoundException()
    }

    override fun update(post: Post): Boolean {
        for ((index, target) in posts.withIndex()) {
            if (target.id == post.id) {
                posts[index] = post.copy(id = target.id, ownerID = target.ownerID, date = target.date)
                return true
            }
        }
        return false
    }

    private fun getId(): Int {
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

    override fun delete(post: Post): Boolean {
        for (target in posts) {
            if (post.id == target.id) {
                posts.remove(target)
                return true
            }
        }
        throw PostNotFoundException()
    }

    override fun print(elem: Post) {
        for (post in posts) {
            println(post)
        }

    }


}