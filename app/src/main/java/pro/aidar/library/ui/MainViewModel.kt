package pro.aidar.library.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import pro.aidar.library.data.data.add_book.use_case.AddBookUseCase
import pro.aidar.library.data.data.get_books.use_case.GetBooksUseCase
import pro.aidar.library.data.dto.Book
import pro.aidar.library.data.dto.Event

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase,
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val disposable: CompositeDisposable by lazy {
        return@lazy CompositeDisposable()
    }

    val event = MutableLiveData<Event>()

    fun addBook(model: Book) {
        disposable.add(
            addBookUseCase.execute(model)
                .doOnComplete { event.postValue(Event.BookInserted) }
                .doOnError { event.postValue(Event.BookInsertError) }
                .subscribe()
        )
    }

    fun fetchBooks() {
        disposable.add(
            getBooksUseCase.getBooks()
                .subscribe({
                    event.postValue(Event.BooksFetched(it))
                }, {})
        )
    }

    fun fetchBooks(orderBy: Orders) {
        decideOrder(orderBy)
            .subscribe({
                event.postValue(Event.BooksFetched(it))
            }, {})
    }

    fun searchBook(name: String) {
        disposable.add(
            getBooksUseCase.searchBook(name)
                .subscribe({
                    event.postValue(Event.BooksFetched(it))
                }, {})
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
}