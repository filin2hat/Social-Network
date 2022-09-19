package services

object ChatGenerateID {
    private var lastId = 0

    fun getId() = ++lastId

    fun getLastId() = lastId
}