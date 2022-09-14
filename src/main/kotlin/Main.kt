import classes.Comments
import classes.Note
import services.NoteService

fun main() {
    val note = NoteService.add(Note(text = "press button"))
    val editComment = Comments(id = 1, text = "EDIT COMMENT")

    NoteService.addComment(1, Comments(text = "FIRST COMMENT"))
    NoteService.print(note)
    NoteService.editComment(1, 1, editComment)
    NoteService.print(note)
    NoteService.deleteComment(1, 1)
    NoteService.print(note)
    NoteService.restoreComment(1, 1)
    NoteService.print(note)
    NoteService.delete(note)
    NoteService.print(note)
    NoteService.restore(note)
    NoteService.print(note)

}