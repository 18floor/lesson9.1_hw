import org.junit.Test

import org.junit.Assert.*
import ru.netology.ChatService
import ru.netology.User

class ChatServiceTest {

    @Test
    fun addUser() {
        val service = ChatService()
        val user = service.addUser(User(
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
    fun addMessage() {
    }

    @Test
    fun deleteChat() {
    }

    @Test
    fun deleteMessage() {
    }

    @Test
    fun getListMessages() {
    }

    @Test
    fun getUnreadChatsCount() {
    }

    @Test
    fun getUnreadChats() {
    }
}