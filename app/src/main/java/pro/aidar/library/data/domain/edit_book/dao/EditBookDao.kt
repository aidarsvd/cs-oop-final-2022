package pro.aidar.library.data.domain.edit_book.dao

import androidx.room.Dao
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable
import pro.aidar.library.data.dto.Book

@Dao
interface EditBookDao {
    @Update
    fun update(book: Book): Completable
}