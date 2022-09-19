package services

import classes.Comments
import classes.Note
import exceptions.CommentNotFoundException
import exceptions.NoteNotFoundException
import org.junit.Assert.*
import org.junit.Test


class NoteServiceTest {

    @Test
    fun addNoteNotThrow() {
        val note = NoteService.add(Note())
        assertNotEquals(0, note.id)
    }

    @Test(expected = NoteNotFoundException::class)
    fun addNoteThrowPostNotFound() {
        NoteService.add(null)
    }

    @Test
    fun updateNote() {
        val note = NoteService.add(Note())
        val exist = NoteService.update(note.copy(text = "test text"))
        assertTrue(exist)
    }

    @Test(expected = NoteNotFoundException::class)
    fun notUpdateNote() {
        val note = NoteService.add(Note())
        val exist = NoteService.update(note.copy(text = "test text", id = note.id + 1))
        assertFalse(exist)
    }

    @Test(expected = NoteNotFoundException::class)
    fun notUpdateNoteIsDelete() {
        val note = NoteService.add(Note())
        NoteService.delete(note)
        val exist = NoteService.update(note.copy(text = "test text"))
        assertFalse(exist)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteThrowPostNotFound() {
        NoteService.delete(Note(99))
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteIsDeleteThrowPostNotFound() {
        val note = NoteService.add(Note())
        note.isDelete = true
        NoteService.delete(note)
    }

//    @Test
//    fun restoreNote() {
//        val note = NoteService.add(Note())
//        NoteService.delete(note)
//        val result = NoteService.restore(note)
//        assertTrue(result)
//    }

    @Test(expected = NoteNotFoundException::class)
    fun restoreNoteNotDelete() {
        val note = NoteService.add(Note())
        NoteService.restore(note)
    }

    @Test
    fun printNotes() {
        NoteService.print(Note())
    }

    @Test
    fun addCommentNotThrow() {
        val comment = Comments(text = "test")
        NoteService.add(Note())
        NoteService.addComment(1, comment)
    }

    @Test(expected = NoteNotFoundException::class)
    fun addCommentThrowNoteNotFound() {
        val comment = Comments(text = "test")
        NoteService.add(Note())
        NoteService.addComment(999, comment)
    }


//    @Test
//    fun deleteCommentNotThrow() {
//        NoteService.addComment(1, Comments(text = "test"))
//        NoteService.deleteComment(1, 1)
//    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentIsDelete() {
        val comment = Comments(text = "test")
        NoteService.addComment(1, comment)
        comment.isDelete = true
        NoteService.deleteComment(1, 1)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteCommentNoteIsDelete() {
        NoteService.deleteComment(1, 1)
    }


    @Test
    fun getNoteNoZero() {
        val note = NoteService.add(Note())
        val notes = NoteService.get()
        val result = note === notes.last()
        assertTrue(result)
    }

    @Test
    fun getByIdNoZero() {
        val note = NoteService.add(Note())
        val newNote = NoteService.getById(note.id)
        val result = note === newNote
        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getByIdThrow() {
        val note = NoteService.add(Note())
        val falseNoteId = note.id + 999
        NoteService.getById(falseNoteId)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getByIdIsDelete() {
        val note = NoteService.add(Note())
        val deleteNoteId = note.id
        note.isDelete = true
        NoteService.getById(deleteNoteId)

    }

    @Test
    fun getComments() {
        val note = NoteService.add(Note())
        val comment = NoteService.addComment(note.id, Comments())
        val comments = NoteService.getComments(note.id)
        val result = comment === comments.last()
        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentShodThrow() {
        val note = NoteService.add(Note())
        val falseNoteId = note.id + 1
        NoteService.getComments(falseNoteId)
    }

    @Test
    fun restoreCommentNoZero() {
        val note = NoteService.add(Note())
        val comment = NoteService.addComment(note.id, Comments())
        val isDeleted = NoteService.deleteComment(note.id, comment.id)
        if (isDeleted) {
            val result = NoteService.restoreComment(note.id, comment.id)
            assertTrue(result)
        }
    }

    @Test(expected = NoteNotFoundException::class)
    fun restoreCommentShodThrowNoteNotFoundException() {
        val note = NoteService.add(Note())
        val comment = NoteService.addComment(note.id, Comments())
        val isDeleted = NoteService.deleteComment(note.id, comment.id)
        val falseNoteId = note.id + 1
        if (isDeleted) NoteService.restoreComment(falseNoteId, comment.id)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentShodThrowCommentNotFoundException() {
        val note = NoteService.add(Note())
        val comment = NoteService.addComment(note.id, Comments())
        val isDeleted = NoteService.deleteComment(note.id, comment.id)
        val falseCommentId = comment.id + 1
        if (isDeleted) NoteService.restoreComment(note.id, falseCommentId)
    }

    @Test
    fun editCommentShouldNotThrow() {
        val note = NoteService.add(Note())
        val comment = NoteService.addComment(note.id, Comments(note.id))
        val newComment = comment.copy(text = "New text")
        val result = NoteService.editComment(note.id, comment.id, newComment)
        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editCommentShouldThrowNoteNotFoundException() {
        val note = NoteService.add(Note())
        val comment = NoteService.addComment(note.id, Comments(note.id))
        val newComment = comment.copy(text = "New text")
        val falseNoteId = note.id + 1
        NoteService.editComment(falseNoteId, comment.id, newComment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentShouldThrowCommentNotFoundException() {
        val note = NoteService.add(Note())
        val comment = NoteService.addComment(note.id, Comments(note.id))
        val newComment = comment.copy(text = "New text")
        val falseCommentId = comment.id + 1
        NoteService.editComment(note.id, falseCommentId, newComment)
    }
}