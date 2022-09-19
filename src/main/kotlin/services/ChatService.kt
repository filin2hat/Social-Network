package services

import classes.Chat
import classes.Message
import exceptions.ChatNotFoundExceptions

object ChatService {
    private val chats = mutableListOf<Chat>()
    private var chatId = GenerateID()
    private var messageId = GenerateID()

    private fun getUsersChats(userId: Int) =
        chats.filter { (it.userId1 == userId || it.userId2 == userId) && it.messages.isNotEmpty() && !it.messages.last().isRead }

    fun addChat(userId: Int, newChat: Chat, message: Message): Chat {
        val userChat = getUsersChats(userId)
        for (chat in userChat) {
            if ((chat.userId1 == newChat.userId1 && chat.userId2 == newChat.userId2) ||
                (chat.userId1 == newChat.userId2 && chat.userId2 == newChat.userId1)
            ) {
                chat.messages += message.copy(id = messageId.getId())
                return chat
            }
        }
        newChat.messages += message.copy(id = messageId.getId())
        chats += newChat.copy(id = chatId.getId(), userId1 = message.fromId, userId2 = message.toId)
        return chats.last()
    }

    fun deleteChat(userId: Int, chatId: Int): Boolean {
        val usersChat = getUsersChats(userId)
        for (chat in usersChat) {
            if (chat.id == chatId) {
                if (chat.messages.isNotEmpty()) {
                    for (message in chat.messages) {
                        chat.messages.remove(message)
                    }
                }
                chats.remove(chat)
                return true
            }
        }
        throw ChatNotFoundExceptions()
    }

    fun editChat(userId: Int, chatId: Int, chat: Chat): Boolean {
        val usersChat = getUsersChats(userId)
        for ((index, thisChat) in usersChat.withIndex()) {
            if (thisChat.id == chatId) {
                chats[index] = chat.copy(
                    userId1 = thisChat.userId1,
                    userId2 = thisChat.userId2,
                    date = thisChat.date
                )
                return true
            }
        }
        throw ChatNotFoundExceptions()
    }

    fun getChats(userId: Int): List<Chat> {
        val result =
            chats.filter { it.userId1 == userId || it.userId2 == userId }
        if (result.isEmpty()) throw ChatNotFoundExceptions()
        return result
    }

    fun clear() {
        chats.clear()
        chatId = GenerateID()
        messageId = GenerateID()
    }
}

