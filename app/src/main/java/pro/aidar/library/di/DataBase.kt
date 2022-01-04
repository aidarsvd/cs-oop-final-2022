package pro.aidar.library.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import pro.aidar.library.db.BookDataBase

@InstallIn(SingletonComponent::class)
@Module
object DataBase {
    @Provides
    @Singleton
    fun provideBookDatabase(
        @ApplicationContext context: Context
    ): BookDataBase {
        return Room.databaseBuilder(
            context,
            BookDataBase::class.java,
            "book_database"
        ).allowMainThreadQueries()
            .build()
    }
}