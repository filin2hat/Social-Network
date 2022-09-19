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

    private fun getChats(userId: Int): List<Chat> {
        val result =
            chats.filter { it.userId1 == userId || it.userId2 == userId }
        if (result.isEmpty()) throw ChatNotFoundException()
        return result
    }

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
        throw ChatNotFoundException()
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
        throw ChatNotFoundException()
    }

    fun getChatById(userId: Int, chatId: Int): Chat {
        val usersChat = getUsersChats(userId)
        for (chat in usersChat) {
            if (chat.id == chatId)
                return chat
        }
        throw ChatNotFoundException()
    }

    fun getUnreadChatsCount(userId: Int): Int {
        var resultCount = 0
        val resultChats = chats.filter {
            (it.userId1 == userId || it.userId2 == userId)
        }
        if (resultChats.isEmpty()) return 0

        for (chat in resultChats) {
            for (massage in chat.messages) {
                if (!massage.isRead && massage.toId == userId) resultCount++
            }
        }
        return resultCount
    }

    fun addMessage(userId: Int, chatId: Int, message: Message): Message {
        val usersChat = getUsersChats(userId)
        for (chat in usersChat) {
            if (chat.id == chatId) {
                chat.messages += message.copy(id = messageId.getId())
                return chat.messages.last()
            }
        }
        throw ChatNotFoundException()
    }

    fun deleteMessage(userId: Int, chatId: Int, massageId: Int): Boolean {
        val usersChat = getUsersChats(userId)
        for (chat in usersChat) {
            if (chat.id == chatId) {
                for (message in ArrayList(chat.messages)) {
                    if (message.id == massageId) {
                        chat.messages.remove(message)
                        if (chat.messages.isEmpty()) deleteChat(userId, chatId)
                        return true
                    }
                }
                throw MessageNotFoundException()
            }
        }
        throw ChatNotFoundException()
    }

    fun editMessage(userId: Int, chatId: Int, messageId: Int, message: Message): Boolean {
        val usersChat = getUsersChats(userId)
        for (chat in usersChat) {
            if (chat.id == chatId) {
                for ((index, thisMessage) in chat.messages.withIndex()) {
                    if (thisMessage.id == messageId) {
                        chat.messages[index] = message.copy(
                            id = thisMessage.id,
                            fromId = thisMessage.fromId,
                            toId = thisMessage.toId,
                            isEdit = true,
                            date = thisMessage.date
                        )
                        return true
                    }
                }
                throw MessageNotFoundException()
            }
        }
        throw ChatNotFoundException()
    }

    fun getMessages(userId: Int, chatId: Int, lastMessageId: Int, countOfMessages: Int): List<Message> {
        val usersChat = getUsersChats(userId)
        for (chat in usersChat) {
            if (chat.id == chatId) {
                for (message in chat.messages) {
                    if (message.id == lastMessageId) {
                        val firstIndex = chat.messages.indexOf(message)
                        val lastIndex =
                            if ((firstIndex + countOfMessages - 1) > chat.messages.size) chat.messages.size else (firstIndex + countOfMessages - 1)
                        val result = chat.messages.filterIndexed { index, _ -> index in firstIndex..lastIndex }
                        result.forEach { it.isRead = true }
                        return result
                    }
                }
                throw MessageNotFoundException()
            }
        }
        throw ChatNotFoundException()
    }

    fun getMessageById(userId: Int, chatId: Int, messageId: Int): Message {
        val usersChat = getUsersChats(userId)
        for (chat in usersChat) {
            if (chat.id == chatId) {
                for (message in chat.messages) {
                    if (message.id == messageId)
                        return message
                }
                throw MessageNotFoundException()
            }
        }
        throw ChatNotFoundException()
    }
}

