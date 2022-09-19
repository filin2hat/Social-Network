import classes.Chat
import classes.Message
import classes.User
import services.ChatService
import services.UserService

fun main() {
    val user1 = UserService.add(User("Petya", "Petrov", 28))
    val user2 = UserService.add(User("Lena", "Ivanova", 23))

    val text = "Hi!"
    val chat = ChatService.addChat(user1.id, Chat(user1.id, user2.id), Message(user1.id, user2.id, text))
    println (ChatService.getUnreadChatsCount(user2.id))
    println(ChatService.getChats(user1.id))
}

