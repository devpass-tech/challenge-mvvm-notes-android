package com.devpass.mynotes.di

import android.content.Context
import androidx.room.Room
import com.devpass.mynotes.data.NoteDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDao(noteDataBase: NoteDataBase) = noteDataBase.getNotesDao()

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
            appContext,
            NoteDataBase::class.java,
            "note_table"
        ).build()
}