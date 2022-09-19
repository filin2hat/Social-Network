package services

class GenerateID {
    private var lastId = 0

    fun getId() = ++lastId

    fun getLastId() = lastId
}