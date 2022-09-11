package services

import classes.Note
import classes.Post
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun addPostNotThrow() {
        val note = NoteService.add(Note())
        assertNotEquals(0, note.id)
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }

    @Test
    fun restore() {
    }

    @Test
    fun print() {
    }

    @Test
    fun addComment() {
    }

    @Test
    fun deleteComment() {
    }

    @Test
    fun editComment() {
    }

    @Test
    fun get() {
    }

    @Test
    fun getById() {
    }

    @Test
    fun getComments() {
    }

    @Test
    fun restoreComment() {
    }
}