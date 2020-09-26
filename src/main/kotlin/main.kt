package ru.netology

fun main() {
    val service = ChatService()
    service.addUser(User(
            userId = 1,
            fullName = "Bill"
    ))
    service.addUser(User(
            userId = 2,
            fullName = "Tommy"
    ))
    service.addUser(User(
            userId = 3,
            fullName = "Larry"
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
    service.addMessage(userId = 2, Message(
            messageId = 1,
            chatId = 1,
            userTo = 2,
            userFrom = 1,
            messageText = "From Bill to Tommy",
            date = 123123123,
            read = false
    ))
    service.addMessage(userId = 2, Message(
            messageId = 1,
            chatId = 2,
            userTo = 2,
            userFrom = 3,
            messageText = "From Larry to Tommy",
            date = 123123123,
            read = false
    ))
    service.addMessage(userId = 1, Message(
            messageId = 1,
            chatId = 3,
            userTo = 1,
            userFrom = 3,
            messageText = "From Larry to Bill",
            date = 123123123,
            read = false
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
    service.addMessage(userId = 3, Message(
            messageId = 1,
            chatId = 3,
            userTo = 3,
            userFrom = 1,
            messageText = "From Bill to Larry",
            date = 123123123,
            read = false
    ))

    println(service)

    println("Incoming unread chats: " + service.getUnreadChatsCount(userId = 1))
    println("Incoming users chats: " + service.getUnreadChats(userId = 1))

//    println(service.deleteChat(chatId = 1))
//    println(service.getListMessages(chatId = 1, messageId = 1))

//    println(service)
}