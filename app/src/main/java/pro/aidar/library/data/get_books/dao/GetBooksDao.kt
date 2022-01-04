package pro.aidar.library.data.get_books.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable
import pro.aidar.library.data.dto.Book

@Dao
interface GetBooksDao {
    @Query("select id, name from book_table")
    fun getBooks(): Observable<List<Book>>

    @Query("select * from book_table where id=:id")
    fun getBookDetail(id: Int): Observable<Book>
}