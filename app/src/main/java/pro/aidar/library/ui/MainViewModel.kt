package pro.aidar.library.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import pro.aidar.library.data.data.add_book.use_case.AddBookUseCase
import pro.aidar.library.data.dto.Book
import pro.aidar.library.data.dto.Event

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase
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
}