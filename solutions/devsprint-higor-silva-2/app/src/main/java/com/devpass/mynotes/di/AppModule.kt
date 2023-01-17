package com.devpass.mynotes.di

import android.content.Context
import androidx.room.Room
import com.devpass.mynotes.data.NoteDataBase
import com.devpass.mynotes.data.repository.NoteRepository
import com.devpass.mynotes.domain.usecase.AddNoteUseCase
import com.devpass.mynotes.domain.usecase.GetNotesUseCase
import com.devpass.mynotes.domain.usecase.NotesManagerUseCase
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
    fun provideNoteRepository(dataBase: NoteDataBase): NoteRepository =
        NoteRepository(dataBase.getNotesDao())

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        NoteDataBase::class.java,
        "note_table"
    ).build()

    @Singleton
    @Provides
    fun provideNotesManager(repository: NoteRepository): NotesManagerUseCase =
        NotesManagerUseCase(
            add = AddNoteUseCase(repository),
            getAll = GetNotesUseCase(repository)
        )
}