package pro.aidar.library.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import pro.aidar.library.data.data.add_book.use_case.AddBookUseCase
import pro.aidar.library.data.data.delete_book.use_case.DeleteBookUseCase
import pro.aidar.library.data.data.edit_book.use_case.EditBookUseCase
import pro.aidar.library.data.data.get_books.use_case.GetBooksUseCase
import pro.aidar.library.data.dto.Book
import pro.aidar.library.data.dto.Event

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase,
    private val getBooksUseCase: GetBooksUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val editBookUseCase: EditBookUseCase
) : ViewModel() {

    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    val event = MutableLiveData<Event>()

    fun addBook(model: Book) {
        disposable.add(
            addBookUseCase.execute(model)
                .doOnComplete { event.postValue(Event.BooksUpdated) }
                .doOnError { event.postValue(Event.BookInsertError) }
                .subscribe()
        )
    }

    fun fetchBooks() {
        disposable.add(
            getBooksUseCase.getBooks()
                .subscribe({
                    event.postValue(Event.BooksFetched(it))
                }, ::handleError)
        )
    }

    fun fetchBooks(orderBy: Orders) {
        decideOrder(orderBy)
            .subscribe({
                event.postValue(Event.BooksFetched(it))
            }, ::handleError)
    }

    fun searchBook(name: String) {
        disposable.add(
            getBooksUseCase.searchBook(name)
                .subscribe({
                    event.postValue(Event.BooksFetched(it))
                }, ::handleError)
        )
    }

    fun deleteBook(model: Book) {
        disposable.add(
            deleteBookUseCase.execute(model)
                .doOnError(::handleError)
                .doOnComplete { event.postValue(Event.BooksUpdated) }
                .subscribe()
        )
    }

    fun updateBook(model: Book) {
        disposable.add(
            editBookUseCase.execute(model)
                .doOnError(::handleError)
                .doOnComplete { event.postValue(Event.BooksUpdated) }
                .subscribe()
        )
    }

    private fun decideOrder(orderBy: Orders): Observable<List<Book>> {
        return when (orderBy) {
            Orders.SIZE -> getBooksUseCase.getBooksBySize()
            Orders.DATE -> getBooksUseCase.getBooksByDate()
            else -> getBooksUseCase.getBooksByName()
        }
    }

    enum class Orders {
        SIZE, DATE, NAME
    }

    private fun handleError(it: Throwable) {
        it.stackTrace
        event.postValue(Event.Notification(it.localizedMessage))
    }
}