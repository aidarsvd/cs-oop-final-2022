package pro.aidar.library.data.data.edit_book.use_case

import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject
import pro.aidar.library.data.data.edit_book.repository.EditBookRepository
import pro.aidar.library.data.dto.Book

class EditBookUseCase @Inject constructor(
    private val repository: EditBookRepository
) {
    fun execute(book: Book): Completable {
        return repository.editBook(book)
    }
}