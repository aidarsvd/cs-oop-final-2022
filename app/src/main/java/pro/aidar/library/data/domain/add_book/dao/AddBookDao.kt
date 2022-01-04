package pro.aidar.library.data.domain.add_book.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.rxjava3.core.Completable
import pro.aidar.library.data.dto.Book

@Dao
interface AddBookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book: Book): Completable
}