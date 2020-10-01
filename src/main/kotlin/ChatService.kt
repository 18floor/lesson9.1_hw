package ru.netology

class ChatService {

    private var users: MutableList<User> = mutableListOf()
    private var messages: MutableList<Message> = mutableListOf()

    private var userIdInc: Int = 1
    private var messageIdInc: Int = 1
    private var chatIdInc: Int = 1

    fun addUser(user: User): User {
        users.plusAssign(user.copy(userId = userIdInc++))
        return users.last()
    }

    fun addMessage(userId: Int, message: Message): Int {

        val userCheck: Boolean = users.last().userId >= userId

        if (makeChat(userId, message.userFrom) == 0 && userCheck) {
            messages.plusAssign(message.copy(messageId = messageIdInc++, chatId = chatIdInc++, userTo = userId))
            return messageIdInc
        } else if (userCheck) {
            messages.plusAssign(message.copy(messageId = messageIdInc++, chatId = makeChat(userId, message.userFrom),
                    userTo = userId))
            return messageIdInc
        }
        return 0
    }

    private fun makeChat(userId: Int, userFrom: Int): Int {
        for (message in messages) {
            if ((message.userTo == userId || message.userTo == userFrom)
                    && (message.userFrom == userFrom || message.userFrom == userId)) {
                return message.chatId
            }
        }
        return 0
    }

    fun deleteChat(chatId: Int): Boolean {
        return messages.removeIf { it.chatId == chatId }
    }

    fun deleteMessage(messageId: Int): Boolean {
        return messages.removeIf { it.messageId == messageId }
    }

    fun getListMessages(chatId: Int, messageId: Int): List<Message> {
        val result = messages.filter { message ->
            message.chatId == chatId && !message.read
        }.takeLastWhile { it.messageId > messageId }

        messages.filter { message ->
            message.chatId == chatId
        }.forEach { message ->
            message.read = true
        }
        return result
    }

    fun getUnreadChatsCount(userId: Int): Int {
        return messages.filter { message ->
            !message.read && message.userTo == userId
        }.groupBy { it.chatId }.count()
    }

    fun getUnreadChats(userId: Int): MutableMap<Int, User> {
        val result = mutableMapOf<Int, User>()

        for (message in messages) {
            if (!message.read && message.userTo == userId) {
                for (user in users) {
                    if (user.userId == message.userFrom) {
                        result[message.chatId] = user
                    }
                }
            }
        }
        return result
    }

    override fun toString(): String {
        return "users=$users\nmessages=$messages"
    }
}