package pro.aidar.library.data.data.edit_book.repository

import io.reactivex.rxjava3.core.Completable
import pro.aidar.library.data.dto.Book

interface EditBookRepository {
    fun editBook(book: Book): Completable
}