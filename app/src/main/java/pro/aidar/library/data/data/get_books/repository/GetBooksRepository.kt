package pro.aidar.library.data.data.get_books.repository

import io.reactivex.rxjava3.core.Observable
import pro.aidar.library.data.dto.Book

interface GetBooksRepository {
    fun getBooks(): Observable<List<Book>>
}