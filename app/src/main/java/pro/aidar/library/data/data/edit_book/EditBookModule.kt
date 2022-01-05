package pro.aidar.library.data.data.edit_book

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import pro.aidar.library.data.data.edit_book.repository.EditBookRepository
import pro.aidar.library.data.domain.edit_book.dao.EditBookDao
import pro.aidar.library.data.domain.edit_book.repository.EditBookRepositoryImpl
import pro.aidar.library.db.BookDataBase

@Module
@InstallIn(SingletonComponent::class)
object EditBookModule {

    @Singleton
    @Provides
    fun provideRepo(dao: EditBookDao): EditBookRepository = EditBookRepositoryImpl(dao)

    @Singleton
    @Provides
    fun provideDao(dataBase: BookDataBase): EditBookDao = dataBase.updateBook()
}