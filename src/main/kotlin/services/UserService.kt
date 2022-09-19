package services

import classes.User
import exceptions.UserNotFoundException

object UserService {
    private val users = mutableListOf<User>()
    private val userId = GenerateID()

    fun add(user: User): User {
        users += user.copy(id = userId.getId())
        return users.last()
    }

    fun delete(userId: Int): Boolean {
        for (user in users) {
            if (user.id == userId && !user.isDeleted) {
                user.isDeleted = true
                return true
            }
        }
        throw UserNotFoundException()
    }

    fun restore(userId: Int): Boolean {
        for (user in users) {
            if (user.id == userId && user.isDeleted) {
                user.isDeleted = false
                return true
            }
        }
        throw UserNotFoundException()
    }

    fun get() = users

    fun getById(userId: Int): User {
        for (user in users) {
            if (user.id == userId && !user.isDeleted)
                return user
        }
        throw UserNotFoundException()
    }

    fun edit(userId: Int, user: User): Boolean {
        for ((index, thisUser) in users.withIndex()) {
            if (thisUser.id == userId && !thisUser.isDeleted) {
                users[index] = user.copy(id = thisUser.id)
                return true
            }
        }
        throw UserNotFoundException()
    }
}