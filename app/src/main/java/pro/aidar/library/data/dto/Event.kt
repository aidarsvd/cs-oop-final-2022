package pro.aidar.library.data.dto

sealed class Event {
    object BooksUpdated : Event()
    object BookInsertError : Event()
    class BooksFetched(val list: List<Book>) : Event()
    class Notification(val message: String?): Event()
}
