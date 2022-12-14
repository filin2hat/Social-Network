@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package services

import classes.Comments
import classes.Note
import exceptions.CommentNotFoundException
import exceptions.NoteNotFoundException
import interfaces.CrudService

object NoteService : CrudService<Note> {
    private val notes = mutableListOf<Note>()
    private val getId = GenerateID()
//    private var lastID = 0

//    private fun getId(): Int {
//        lastID += 1
//        return lastID
//    }

    override fun add(note: Note?): Note {
        if (note != null) {
            notes += note.copy(id = getId.getId())
            return notes.last()
        }
        throw NoteNotFoundException()
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

    fun restore(note: Note): Boolean {
        for (target in notes) {
            if (note.id == target.id && note.isDelete) {
                note.isDelete = false
                return true
            }
        }
        throw NoteNotFoundException()
    }

    override fun print(note: Note) {
        for (target in notes) {
            println(target)
        }
    }

    fun addComment(noteId: Int, comments: Comments): Comments {
        for (note in notes) {
            if (note.id == noteId && !note.isDelete) {
                note.comments += comments.copy(id = getId.getId())
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
                        return true
                    }
                }
                throw CommentNotFoundException()
            }
        }
        throw NoteNotFoundException()
    }

    // ?????????????????????? ?????????????????? ?????????????????????? ?? ??????????????.
    fun editComment(noteId: Int, commentId: Int, comment: Comments): Boolean {
        for (note in notes) {
            if (note.id == noteId && !note.isDelete) {
                for ((index, thisComment) in note.comments.withIndex()) {
                    if (thisComment.id == commentId && !comment.isDelete) {
                        notes[index] = note.copy(
                            id = thisComment.id,
                            ownerId = thisComment.ownerId,
                            date = thisComment.date
                        )
                        return true
                    }
                }
                throw CommentNotFoundException()
            }
        }
        throw NoteNotFoundException()
    }

    // ???????????????????? ???????????? ??????????????, ?????????????????? ??????????????????????????.
    fun get() = notes

    //???????????????????? ???????????? ???????????????????????? ?? ??????????????.
    fun getById(noteId: Int): Note {
        for (note in notes) {
            if (noteId == note.id && !note.isDelete) return note
        }
        throw NoteNotFoundException()
    }

    //???????????????????? ???????????? ???????????????????????? ?? ??????????????.
    fun getComments(noteId: Int): MutableList<Comments> {
        for (note in notes) {
            if (note.id == noteId && !note.isDelete) return note.comments
        }
        throw NoteNotFoundException()
    }

    //?????????????????????????????? ?????????????????? ??????????????????????.
    fun restoreComment(noteId: Int, commentId: Int): Boolean {
        for (note in notes) {
            if (note.id == noteId && !note.isDelete) {
                for (comment in note.comments) {
                    if (comment.id == commentId && comment.isDelete) {
                        comment.isDelete = false
                        return true
                    }
                }
                throw CommentNotFoundException()
            }
        }
        throw NoteNotFoundException()
    }
}