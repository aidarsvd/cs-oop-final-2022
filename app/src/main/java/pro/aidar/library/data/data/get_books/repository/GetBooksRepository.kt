package pro.aidar.library.data.data.get_books.repository

import io.reactivex.rxjava3.core.Observable
import pro.aidar.library.data.dto.Book

interface GetBooksRepository {
    fun getBooks(): Observable<List<Book>>
    fun searchBook(name: String): Observable<List<Book>>
    fun getBooksByName(): Observable<List<Book>>
    fun getBookByDate(): Observable<List<Book>>
    fun getBookBySize(): Observable<List<Book>>
    fun getBook(id: Int): Observable<Book>
}