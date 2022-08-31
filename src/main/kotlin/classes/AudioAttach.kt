package classes

import interfaces.Attachment

data class AudioAttach(val audio: Audio) : Attachment {
    override val type: String = ""

}