package services

import classes.Comments
import classes.Note
import exceptions.CommentNotFoundException
import exceptions.NoteNotFoundException
import interfaces.CrudService

object NoteService : CrudService<Note> {
    private val notes = mutableListOf<Note>()
    private var lastID = 0

    private fun getId(): Int {
        lastID += 1
        return lastID
    }

    override fun add(note: Note): Note {
        notes += note.copy(id = getId())
        return notes.last()
    }

    override fun update(note: Note): Boolean {
        for ((index, target) in notes.withIndex()) {
            if (target.id == note.id && !note.isDelete) {
                notes[index] = note.copy(id = target.id, ownerId = target.ownerId, date = target.date)
                return true
            }
        }
        throw NoteNotFoundException()
    }

    override fun delete(note: Note): Boolean {
        for (target in notes) {
            if (note.id == target.id && !note.isDelete) {
                note.isDelete = true
                return true
            }
        }
        throw NoteNotFoundException()
    }

    override fun print(note: Note): Boolean {
        for (target in notes) {
            println(target)
            return true
        }
        throw NoteNotFoundException()
    }

    fun addComment(noteId: Int, comments: Comments): Comments {
        for (note in notes) {
            if (note.id == noteId && !note.isDelete) {
                note.comments += comments.copy(id = CommentsGenerateID.getId())
                return note.comments.last()
            }
        }
        throw NoteNotFoundException()
    }

    fun deleteComment(noteId: Int, commentId: Int): Boolean {
        for (note in notes) {
            if (note.id == noteId && !note.isDelete) {
                for (comment in note.comments) {
                    if (comment.id == commentId && !comment.isDelete) {
                        comment.isDelete = true
                    }
                }
                throw CommentNotFoundException()
            }
        }
        throw NoteNotFoundException()
    }

    // Редактирует указанный комментарий у заметки.
    fun editComment(noteId: Int, commentId: Int, comments: Comments): Boolean {
        for (note in notes) {
            if (note.id == noteId && !note.isDelete) {
                for ((index, targetComment) in note.comments.withIndex()) {
                    if (targetComment.id == commentId && !comments.isDelete) {
                        notes[index] =
                            note.copy(id = targetComment.id, date = targetComment.date, ownerId = targetComment.ownerId)
                        return true
                    }
                }
                throw CommentNotFoundException()
            }
        }
        throw NoteNotFoundException()
    }

    // Возвращает список заметок, созданных пользователем.
    fun get() = notes

    //Возвращает список комментариев к заметке.
    fun getById(noteId: Int): Note {
        for (note in notes) {
            if (noteId == note.id && !note.isDelete) return note
        }
        throw NoteNotFoundException()
    }

    //Восстанавливает удалённый комментарий.
    fun restoreComment(noteId: Int, commentId: Int): Boolean {
        for (note in notes) {
            if (note.id == noteId && !note.isDelete) {
                for (comment in note.comments) {
                    if (comment.id == commentId && comment.isDelete) {
                        comment.isDelete = false
                    }
                }
                throw CommentNotFoundException()
            }
        }
        throw NoteNotFoundException()
    }
}