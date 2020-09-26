import org.junit.Test

import org.junit.Assert.*
import ru.netology.ChatService
import ru.netology.Message
import ru.netology.User

class ChatServiceTest {

    @Test
    fun addUser() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        val user2 = service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        assertEquals(user2.userId, 2)
    }

    @Test
    fun addMessageNewChatTrue() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        val message = Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        )

        val result = service.addMessage(userId = 1, message)
        assertEquals(result, 2)
    }

    @Test
    fun addMessageWrongUser() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        val message = Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        )

        val result = service.addMessage(userId = 10, message)
        assertEquals(result, 0)
    }

    @Test
    fun addMessageOldChatTrue() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        ))
        val result = service.addMessage(userId = 2, Message(
                messageId = 1,
                chatId = 1,
                userTo = 2,
                userFrom = 1,
                messageText = "From Bill to Tommy",
                date = 123123123,
                read = false
        ))
        assertEquals(result, 3)
    }

    @Test
    fun addMessageTwoNewChatTrue() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        ))
        val result = service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 3,
                messageText = "From Bill to Tommy",
                date = 123123123,
                read = false
        ))
        assertEquals(result, 3)
    }

    @Test
    fun deleteChatTrue() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        ))

        val result = service.deleteChat(chatId = 1)
        assertTrue(result)
    }

    @Test
    fun deleteChatFalse() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        ))

        val result = service.deleteChat(chatId = 10)
        assertFalse(result)
    }

    @Test
    fun deleteMessageTrue() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        ))

        val result = service.deleteMessage(messageId = 1)
        assertTrue(result)
    }

    @Test
    fun deleteMessageFalse() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        ))

        val result = service.deleteMessage(messageId = 10)
        assertFalse(result)
    }

    @Test
    fun getListMessagesTrue() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        val message = Message(
                messageId = 2,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = true
        )
        val message2 = Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        )
        val expected = listOf(message)
        service.addMessage(userId = 1, message)
        service.addMessage(userId = 1, message2)
        val result = service.getListMessages(chatId = 1, messageId = 1)
        assertEquals(result, expected)

    }

    @Test
    fun getListMessagesChatIdFalse() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        val message = Message(
                messageId = 2,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = true
        )
        val message2 = Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = true
        )
        service.addMessage(userId = 1, message)
        service.addMessage(userId = 1, message2)
        val result = service.getListMessages(chatId = 10, messageId = 1)
        assertEquals(result, emptyList<Message>())

    }

    @Test
    fun getListMessagesReadFalse() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        val message = Message(
                messageId = 2,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        )
        val message2 = Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        )
        service.addMessage(userId = 1, message)
        service.addMessage(userId = 1, message2)
        service.getListMessages(chatId = 1, messageId = 1)
        val result = service.getListMessages(chatId = 1, messageId = 1)

        assertEquals(result, emptyList<Message>())

    }

    @Test
    fun getUnreadChatsCountTrue() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 3,
                messageText = "From Bill to Tommy",
                date = 123123123,
                read = true
        ))
        val result = service.getUnreadChatsCount(userId = 1)
        assertEquals(result, 1)

    }

    @Test
    fun getUnreadChatsCountFalse() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 3,
                messageText = "From Bill to Tommy",
                date = 123123123,
                read = true
        ))
        val result = service.getUnreadChatsCount(userId = 10)
        assertEquals(result, 0)

    }

    @Test
    fun getUnreadChatsCountNone() {
        val service = ChatService()
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addUser(User(
                userId = 1,
                fullName = "Bill"
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = true
        ))
        service.addMessage(userId = 1, Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 3,
                messageText = "From Bill to Tommy",
                date = 123123123,
                read = true
        ))
        val result = service.getUnreadChatsCount(userId = 1)
        assertEquals(result, 0)

    }

    @Test
    fun getUnreadChats() {
        val service = ChatService()
        val user = User(
                userId = 1,
                fullName = "Bill"
        )
        val user1 = User(
                userId = 1,
                fullName = "Bill"
        )
        val message = Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 2,
                messageText = "From Tommy to Bill",
                date = 123123123,
                read = false
        )
        val message1 = Message(
                messageId = 1,
                chatId = 1,
                userTo = 1,
                userFrom = 1,
                messageText = "From Bill to Tommy",
                date = 123123123,
                read = false
        )
        val expected = mutableMapOf(1 to user)
        service.addUser(user)
        service.addUser(user1)
        service.addMessage(userId = 1, message)
        service.addMessage(userId = 2, message1)
        val result = service.getUnreadChats(userId = 2)
        assertEquals(result, expected)

    }

}