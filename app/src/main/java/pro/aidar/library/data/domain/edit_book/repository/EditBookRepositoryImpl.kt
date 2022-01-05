package pro.aidar.library.data.domain.edit_book.repository

import io.reactivex.rxjava3.core.Completable
import pro.aidar.library.data.data.edit_book.repository.EditBookRepository
import pro.aidar.library.data.domain.edit_book.dao.EditBookDao
import pro.aidar.library.data.dto.Book

class EditBookRepositoryImpl(
    private val dao: EditBookDao
) : EditBookRepository {
    override fun editBook(book: Book): Completable {
        return dao.update(book)
    }
}