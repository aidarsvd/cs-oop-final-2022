package pro.aidar.library.data.domain.add_book.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import pro.aidar.library.data.data.add_book.repository.AddBookRepository
import pro.aidar.library.data.domain.add_book.dao.AddBookDao
import pro.aidar.library.data.dto.Book

class AddBookRepositoryImpl @Inject constructor(
    private val addBookDao: AddBookDao
) : AddBookRepository {
    override fun addBook(book: Book): Completable {
        return addBookDao.addBook(book = book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}