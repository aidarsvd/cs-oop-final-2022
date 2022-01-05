package pro.aidar.library.data.data.delete_book.use_case

import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject
import pro.aidar.library.data.data.delete_book.repository.DeleteBookRepository
import pro.aidar.library.data.dto.Book

class DeleteBookUseCase @Inject constructor(
    private val repository: DeleteBookRepository
) {
    fun execute(book: Book): Completable {
        return repository.deleteBook(book)
    }
}