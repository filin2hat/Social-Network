package services

import classes.Chat
import classes.Message
import exceptions.ChatNotFoundException
import exceptions.MessageNotFoundException

object ChatService {
    private val chats = mutableListOf<Chat>()
    private var chatId = GenerateID()
    private var messageId = GenerateID()

    fun clear() {
        chats.clear()
        chatId = GenerateID()
        messageId = GenerateID()
    }

    fun getChats(userId: Int): List<Chat> {
        val result = chats.filter { it.userId1 == userId || it.userId2 == userId }
        if (result.isEmpty()) throw ChatNotFoundException()
        return result
    }

    fun getUsersChats(userId: Int) =
        chats.filter { (it.userId1 == userId || it.userId2 == userId) && it.messages.isNotEmpty() && !it.messages.last().isRead }

    fun addChat(userId: Int, newChat: Chat, message: Message): Chat {
        val chat = getUsersChats(userId).find {
            (it.userId1 == newChat.userId1 && it.userId2 == newChat.userId1) ||
                    (it.userId1 == newChat.userId2 && it.userId2 == newChat.userId1)
        }
        return if (chat != null) {
            chat.messages += message.copy(id = messageId.getId())
            chat
        } else {
            newChat.messages += message.copy(id = messageId.getId())
            chats += newChat.copy(id = messageId.getId(), userId1 = message.fromId, userId2 = message.toId)
            chats.last()
        }
    }

    fun deleteChat(userId: Int, chatId: Int): Boolean {
        val chat = getChats(userId).find { it.id == chatId } ?: throw ChatNotFoundException()
        chat.messages.clear()
        chats.remove(chat)
        return true
    }

    fun editChat(userId: Int, chatId: Int, chat: Chat): Boolean {
        val thisChat = getChats(userId).find { it.id == chatId } ?: throw ChatNotFoundException()
        chats[chats.indexOf(thisChat)] = chat.copy(
            id = thisChat.id,
            userId1 = thisChat.userId1,
            userId2 = thisChat.userId2,
            date = thisChat.date
        )
        return true
    }

    fun getChatById(userId: Int, chatId: Int) =
        getChats(userId).find { it.id == chatId } ?: throw ChatNotFoundException()


    fun getUnreadChatsCount(userId: Int) = chats
        .filter { (it.userId1 == userId || it.userId2 == userId) }
        .count { chat -> chat.messages.any { !it.isRead && it.toId == userId } }

    fun addMessage(userId: Int, chatId: Int, message: Message): Message {
        val chat = getChats(userId).find { it.id == chatId } ?: throw ChatNotFoundException()
        chat.messages += message.copy(id = messageId.getId())
        return chat.messages.last()
    }

    fun deleteMessage(userId: Int, chatId: Int, messageId: Int): Boolean {
        val chat = getChats(userId).find { it.id == chatId } ?: throw ChatNotFoundException()
        val message = chat.messages.find { it.id == messageId } ?: throw MessageNotFoundException()
        chat.messages.remove(message)
        if (chat.messages.isEmpty()) deleteChat(userId, chatId)
        return true
    }

    fun editMessage(userId: Int, chatId: Int, messageId: Int, message: Message): Boolean {
        val chat = getChats(userId).find { it.id == chatId } ?: throw ChatNotFoundException()
        val thisMessage = chat.messages.find { it.id == messageId } ?: throw MessageNotFoundException()
        chat.messages[chat.messages.indexOf(thisMessage)] = message.copy(
            id = thisMessage.id,
            fromId = thisMessage.fromId,
            toId = thisMessage.toId,
            isEdit = true,
            date = thisMessage.date
        )
        return true
    }

    fun getMessages(userId: Int, chatId: Int, lastMessageId: Int, countOfMessages: Int): List<Message> {
        val chat = getChats(userId).find { it.id == chatId } ?: throw ChatNotFoundException()
        val lastMessage = chat.messages.find { it.id == lastMessageId } ?: throw MessageNotFoundException()
        val firstIndex = chat.messages.indexOf(lastMessage)
        val lastIndex =
            if ((firstIndex + countOfMessages - 1) > chat.messages.size) chat.messages.size else (firstIndex + countOfMessages - 1)
        val result = chat.messages.filterIndexed { index, _ -> index in firstIndex..lastIndex }
        result.forEach { it.isRead = true }
        return result
    }

    fun getMessageById(userId: Int, chatId: Int, messageId: Int): Message {
        val chat = getChats(userId).find { it.id == chatId } ?: throw ChatNotFoundException()
        return chat.messages.find { it.id == messageId } ?: throw MessageNotFoundException()
    }
}

