package services

import classes.Comments
import classes.Post
import classes.Report
import exceptions.CommentNotFoundException
import exceptions.PostNotFoundException
import exceptions.UnknownReasonException

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comments>()
    private var reports = emptyArray<Report>()
    private var lastID = 0

    fun addReport(postId: Int, commentId: Int, report: Report): Report {
        if (report.reason!! < 0 || report.reason > 6) throw UnknownReasonException()
        for (post in posts) {
            if (postId == post.id) {
                for (comment in comments) {
                    if (commentId ==comment.id) {
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