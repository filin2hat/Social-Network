package classes

import interfaces.Attachment

data class DocumentAttach(val document: Document) : Attachment {
    override val type: String = "document"
}

data class Document(
    val id: Int,
    val albumId: Int,
    val ownerId: Long,
    val userId: Long
)