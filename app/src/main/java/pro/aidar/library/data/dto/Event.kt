package pro.aidar.library.data.dto

sealed class Event {
    object BookInserted : Event()
    object BookInsertError : Event()
}
