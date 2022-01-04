package pro.aidar.library.data.data.add_book

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import pro.aidar.library.data.data.add_book.repository.AddBookRepository
import pro.aidar.library.data.domain.add_book.dao.AddBookDao
import pro.aidar.library.data.domain.add_book.repository.AddBookRepositoryImpl
import pro.aidar.library.db.BookDataBase

@Module
@InstallIn(SingletonComponent::class)
object AddBookModule {

    @Provides
    @Singleton
    fun provideDao(dataBase: BookDataBase): AddBookDao {
        return dataBase.addBook()
    }

    @Provides
    @Singleton
    fun provideRepo(dao: AddBookDao): AddBookRepository {
        return AddBookRepositoryImpl(dao)
    }
}