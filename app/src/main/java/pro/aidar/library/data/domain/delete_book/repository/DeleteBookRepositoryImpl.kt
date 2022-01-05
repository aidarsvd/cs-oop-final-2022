package pro.aidar.library.data.domain.delete_book.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import pro.aidar.library.data.data.delete_book.repository.DeleteBookRepository
import pro.aidar.library.data.domain.delete_book.dao.DeleteBookDao
import pro.aidar.library.data.dto.Book

class DeleteBookRepositoryImpl(
    private val deleteBookDao: DeleteBookDao
) : DeleteBookRepository {
    override fun deleteBook(book: Book): Completable {
        return deleteBookDao.deleteBook(book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}