package classes

import interfaces.Attachment
import java.time.LocalDate

data class Post(
    val id: Int = 0,
    val likes: Int = 0,
    val ownerID: Int = 0,
    val date: LocalDate = LocalDate.now(),
    val text: String = "text",
    val comment: Comments? = Comments(),
    var attach: Array<Attachment>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (likes != other.likes) return false
        if (ownerID != other.ownerID) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (comment != other.comment) return false
        if (attach != null) {
            if (other.attach == null) return false
            if (!attach.contentEquals(other.attach)) return false
        } else if (other.attach != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + likes
        result = 31 * result + ownerID
        result = 31 * result + date.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + (comment?.hashCode() ?: 0)
        result = 31 * result + (attach?.contentHashCode() ?: 0)
        return result
    }
}