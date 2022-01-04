package pro.aidar.library.data.domain.get_books.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable
import pro.aidar.library.data.dto.Book

@Dao
interface GetBooksDao {
    @Query("select id, name, size from book_table")
    fun getBooks(): Observable<List<Book>>

    @Query("select * from book_table where id=:id")
    fun getBookDetail(id: Int): Observable<Book>

    @Query("select id, name, size from book_table where name=:name")
    fun searchBookByName(name: String): Observable<List<Book>>

    @Query("select id, name, size from book_table order by name asc")
    fun sortByName(): Observable<List<Book>>

    @Query("select id, name, size from book_table order by size asc")
    fun sortByBookSize(): Observable<List<Book>>

    @Query("select id, name, size from book_table order by updateDate asc")
    fun getBooksByDate(): Observable<List<Book>>
}