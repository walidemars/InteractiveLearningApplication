package com.nikitinsky.interactive.learning.app.di

import android.content.Context
import androidx.room.Room
import com.nikitinsky.interactive.learning.app.data.local.dao.KanaDao
import com.nikitinsky.interactive.learning.app.data.local.dao.LevelDao
import com.nikitinsky.interactive.learning.app.data.local.dao.WordDao
import com.nikitinsky.interactive.learning.app.data.local.database.Database
import com.nikitinsky.interactive.learning.app.data.repository.KanaRepositoryImpl
import com.nikitinsky.interactive.learning.app.data.repository.LevelsRepositoryImpl
import com.nikitinsky.interactive.learning.app.data.repository.MenuRepositoryImpl
import com.nikitinsky.interactive.learning.app.data.repository.WordsRepositoryImpl
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import com.nikitinsky.interactive.learning.app.domain.repository.LevelsRepository
import com.nikitinsky.interactive.learning.app.domain.repository.MenuRepository
import com.nikitinsky.interactive.learning.app.domain.repository.WordsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindKanaRepository(
        kanaImpl: KanaRepositoryImpl
    ): KanaRepository

    @Singleton
    @Binds
    fun bindWordsRepository(
        wordsImpl: WordsRepositoryImpl
    ): WordsRepository

    @Singleton
    @Binds
    fun bindLevelsRepository(
        levelsImpl: LevelsRepositoryImpl
    ): LevelsRepository

    @Singleton
    @Binds
    fun bindMenuRepository(
        menuImpl: MenuRepositoryImpl
    ): MenuRepository

    companion object {

        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext context: Context): Database {
            return Room.databaseBuilder(
                context = context,
                klass = Database::class.java,
                name = "nihongo_database.db"
            ).fallbackToDestructiveMigration(dropAllTables = true).build()
        }

        @Singleton
        @Provides
        fun provideKanaDao(
            database: Database
        ): KanaDao {
            return database.kanaDao()
        }

        @Singleton
        @Provides
        fun provideWordDao(
            database: Database
        ): WordDao {
            return database.wordDao()
        }

        @Singleton
        @Provides
        fun provideLevelDao(
            database: Database
        ): LevelDao {
            return database.levelDao()
        }
    }
}