package com.devpass.mynotes.di

import android.content.Context
import androidx.room.Room
import com.devpass.mynotes.data.NoteDataBase
import com.devpass.mynotes.data.NotesDao
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
    fun provideNoteDao(noteDataBase: NoteDataBase): NotesDao {
        return noteDataBase.getNotesDao()
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext appContext: Context): NoteDataBase {
        return Room.databaseBuilder(
            appContext,
            NoteDataBase::class.java,
            "note_table"
        ).build()
    }
}