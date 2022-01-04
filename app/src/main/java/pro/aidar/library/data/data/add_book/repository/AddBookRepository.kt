package pro.aidar.library.data.data.add_book.repository

import io.reactivex.rxjava3.core.Completable
import pro.aidar.library.data.dto.Book

interface AddBookRepository {
    fun addBook(book: Book): Completable
}