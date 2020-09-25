package ru.netology

@Suppress("UNREACHABLE_CODE")
class ChatService {

    private var users: MutableList<User> = mutableListOf()
    private var messages: MutableList<Message> = mutableListOf()

    private var uid: Int = 1
    private var mid: Int = 1
    private var cid: Int = 1

    fun addUser(user: User): User {
        users.plusAssign(user.copy(userId = uid++))
        return users.last()
    }

    fun addMessage(userId: Int, message: Message): Int {

        val userCheck: Boolean = users.last().userId >= userId

        if (makeChat(userId, message.userFrom) == 0 && userCheck) {
            messages.plusAssign(message.copy(messageId = mid++, chatId = cid++, userTo = userId))
            return mid
        } else if (userCheck) {
            messages.plusAssign(message.copy(messageId = mid++, chatId = makeChat(userId, message.userFrom),
                    userTo = userId))
            return mid
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

    fun getListMessages(chatId: Int, messageId: Int): MutableList<Message> {
        var result: MutableList<Message> = mutableListOf()
        return try {
            result = messages.filter { message ->
                message.chatId == chatId
            }.takeLastWhile { it.messageId > messageId } as MutableList<Message>

            messages.filter { message ->
                message.chatId == chatId
            }.forEach { message ->
                message.read = true
            }
            result
        } catch (e: RuntimeException) {
            result
        }
    }

    fun getUnreadChatsCount(userId: Int): Int {
        return messages.filter { message ->
            !message.read && message.userTo == userId
        }.groupBy { it.chatId }.count()
    }

    fun getUnreadChats(userId: Int): MutableMap<Int, Message> {
        val result = mutableMapOf<Int, Message>()

        for (message in messages) {
            if (!message.read && message.userTo == userId) {
                result[message.chatId] = message
            }
        }
        return result
    }

    override fun toString(): String {
        return "users=$users\nmessages=$messages"
    }
}