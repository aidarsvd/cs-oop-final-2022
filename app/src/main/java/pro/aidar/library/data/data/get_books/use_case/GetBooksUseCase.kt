package pro.aidar.library.data.data.get_books.use_case

import javax.inject.Inject
import pro.aidar.library.data.data.get_books.repository.GetBooksRepository

class GetBooksUseCase @Inject constructor(
    private val repository: GetBooksRepository
) {

    fun getBooks() = repository.getBooks()

}