package pro.aidar.library.data.data.get_books.use_case

import javax.inject.Inject
import pro.aidar.library.data.data.get_books.repository.GetBooksRepository

class GetBooksUseCase @Inject constructor(
    private val repository: GetBooksRepository
) {
    fun getBooks() = repository.getBooks()
    fun getBooksByDate() = repository.getBookByDate()
    fun getBooksBySize() = repository.getBookBySize()
    fun getBooksByName() = repository.getBooksByName()
    fun getBook(id: Int) = repository.getBook(id)
    fun searchBook(name: String) = repository.searchBook(name)
}