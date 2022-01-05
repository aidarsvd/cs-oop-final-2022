package pro.aidar.library.data.data.delete_book.repository

import io.reactivex.rxjava3.core.Completable
import pro.aidar.library.data.dto.Book

interface DeleteBookRepository {
    fun deleteBook(book: Book): Completable
}