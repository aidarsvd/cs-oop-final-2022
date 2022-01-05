package pro.aidar.library.data.data.delete_book

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import pro.aidar.library.data.data.delete_book.repository.DeleteBookRepository
import pro.aidar.library.data.domain.delete_book.dao.DeleteBookDao
import pro.aidar.library.data.domain.delete_book.repository.DeleteBookRepositoryImpl
import pro.aidar.library.db.BookDataBase

@Module
@InstallIn(SingletonComponent::class)
object DeleteBookModule {

    @Singleton
    @Provides
    fun provideDao(dataBase: BookDataBase): DeleteBookDao = dataBase.deleteBook()

    @Singleton
    @Provides
    fun provideRepository(dao: DeleteBookDao): DeleteBookRepository = DeleteBookRepositoryImpl(dao)
}