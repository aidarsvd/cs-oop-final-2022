package pro.aidar.library.data.data.get_books

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import pro.aidar.library.data.data.get_books.repository.GetBooksRepository
import pro.aidar.library.data.domain.get_books.dao.GetBooksDao
import pro.aidar.library.data.domain.get_books.repository.GetBooksRepositoryImpl
import pro.aidar.library.db.BookDataBase

@Module
@InstallIn(SingletonComponent::class)
object GetBooksModule {

    @Singleton
    @Provides
    fun provideDao(dataBase: BookDataBase): GetBooksDao {
        return dataBase.getBooks()
    }

    @Singleton
    @Provides
    fun providesRepo(dao: GetBooksDao): GetBooksRepository {
        return GetBooksRepositoryImpl(dao = dao)
    }
}