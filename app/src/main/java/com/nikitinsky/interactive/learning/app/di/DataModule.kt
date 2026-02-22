package com.nikitinsky.interactive.learning.app.di

import android.content.Context
import androidx.room.Room
import com.nikitinsky.interactive.learning.app.data.local.dao.KanaDao
import com.nikitinsky.interactive.learning.app.data.local.database.KanaDatabase
import com.nikitinsky.interactive.learning.app.data.repository.KanaRepositoryImpl
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
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
        impl: KanaRepositoryImpl
    ): KanaRepository

    companion object {

        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext context: Context): KanaDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = KanaDatabase::class.java,
                name = "japanese_learning.db"
            )
                .createFromAsset("databases/japanese.db")
                .fallbackToDestructiveMigration(true)
                .build()
        }

        @Singleton
        @Provides
        fun provideKanaDao(
            database: KanaDatabase
        ): KanaDao = database.kanaDao()
    }
}