package pro.aidar.library.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import pro.aidar.library.data.data.add_book.use_case.AddBookUseCase
import pro.aidar.library.data.dto.Book

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {

    private val disposable: CompositeDisposable by lazy {
        return@lazy CompositeDisposable()
    }

    fun addBook(model: Book) {
        disposable.add(
            addBookUseCase.execute(model)
                .doOnComplete {

                }
                .subscribe({

                }, {
                    it
                })

        )
    }
}