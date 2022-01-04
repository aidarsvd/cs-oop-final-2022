package pro.aidar.library.data.domain.delete_book.dao

import androidx.room.Dao
import androidx.room.Delete
import pro.aidar.library.data.dto.Book

@Dao
interface DeleteBookDao {
    @Delete
    fun deleteBook(book: Book)
}