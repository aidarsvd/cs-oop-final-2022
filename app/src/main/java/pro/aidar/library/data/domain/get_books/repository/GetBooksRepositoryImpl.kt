package pro.aidar.library.data.domain.get_books.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import pro.aidar.library.data.data.get_books.repository.GetBooksRepository
import pro.aidar.library.data.domain.get_books.dao.GetBooksDao
import pro.aidar.library.data.dto.Book

class GetBooksRepositoryImpl @Inject constructor(
    private val dao: GetBooksDao
) : GetBooksRepository {
    override fun getBooks(): Observable<List<Book>> {
        return dao.getBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}