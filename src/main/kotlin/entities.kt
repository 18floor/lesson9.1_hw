package ru.netology

data class User(
        var userId: Int,
        val fullName: String
)

data class Message(
        val messageId: Int,
        val chatId: Int,
        val userTo: Int,
        val userFrom: Int,
        val messageText: String,
        val date: Long,
        var read: Boolean = false
)