package classes

import interfaces.Attachment

data class DocumentAttach(val document: Document) : Attachment {
    override val type: String = "document"
}