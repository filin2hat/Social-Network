package classes

import interfaces.Attachment

data class VideoAttach(val video: Video) : Attachment {
    override val type: String = ""
}