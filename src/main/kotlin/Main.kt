import classes.*
import services.NoteService

fun main() {
    val note = NoteService.add(Note(text = "press button"))
    NoteService.addComment(1, Comments(text = "FIRST COMMENT"))
    val editComment = Comments(text = "EDIT COMMENT")

    NoteService.print(note)
    NoteService.editComment(1, 1, editComment)
    //println(note.isDelete)
    //NoteService.delete(note)
    //println(note.isDelete)
    NoteService.print(note)

}