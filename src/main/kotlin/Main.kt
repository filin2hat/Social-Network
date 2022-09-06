import classes.*
import services.NoteService
import services.WallService

fun main() {
    val note = NoteService.add(Note(text = "press button"))
    NoteService.addComment(1, Comments())

    println(note.isDelete)
    val result1 = NoteService.delete(note)
    println(note.isDelete)
}