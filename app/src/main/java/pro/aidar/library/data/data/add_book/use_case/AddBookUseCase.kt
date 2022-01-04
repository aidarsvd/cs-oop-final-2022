package pro.aidar.library.data.data.add_book.use_case

import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject
import pro.aidar.library.data.data.add_book.repository.AddBookRepository
import pro.aidar.library.data.dto.Book

class AddBookUseCase @Inject constructor(
    private val repository: AddBookRepository
) {
    fun execute(book: Book): Completable {
        return repository.addBook(book)
    }
}