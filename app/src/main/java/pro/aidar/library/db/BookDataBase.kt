package pro.aidar.library.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pro.aidar.library.data.add_book.dao.AddBookDao
import pro.aidar.library.data.delete_book.dao.DeleteBookDao
import pro.aidar.library.data.dto.Book
import pro.aidar.library.data.edit_book.dao.EditBookDao
import pro.aidar.library.data.get_books.dao.GetBooksDao

@Database(entities = [Book::class], version = 1)
abstract class BookDataBase : RoomDatabase() {
    abstract fun addBook(): AddBookDao
    abstract fun deleteBook(): DeleteBookDao
    abstract fun updateBook(): EditBookDao
    abstract fun getBooks(): GetBooksDao
}