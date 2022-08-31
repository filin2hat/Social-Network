package classes

import interfaces.Attachment

data class MusicAttach(val music: Music) : Attachment {
    override val type: String = "music"
}
