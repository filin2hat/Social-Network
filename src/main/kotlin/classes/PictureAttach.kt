package classes

import interfaces.Attachment

data class PictureAttach(val picture: Picture) : Attachment {
    override val type: String = "picture"
}