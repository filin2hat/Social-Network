package services

class CommentsGenerateID {
    private var lastId: Int = 0

    fun getId(): Int {
        lastId += 1
        return lastId
    }
}