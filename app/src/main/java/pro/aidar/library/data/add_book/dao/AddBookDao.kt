package pro.aidar.library.data.add_book.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import pro.aidar.library.data.dto.Book

@Dao
interface AddBookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book: Book)
}