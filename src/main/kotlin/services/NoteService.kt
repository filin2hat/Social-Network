package services

import classes.Comments
import classes.Note
import exceptions.NoteNotFoundException
import interfaces.CrudService

object NoteService : CrudService<Note> {
    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<Comments>()
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

}