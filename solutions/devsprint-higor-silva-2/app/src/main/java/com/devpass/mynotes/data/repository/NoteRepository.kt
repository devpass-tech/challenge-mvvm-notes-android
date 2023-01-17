package com.devpass.mynotes.data.repository

import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.data.NotesDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val notesDao: NotesDao) {

    suspend fun insert(note: Note) = notesDao.insert(note)

    suspend fun delete(note: Note) = notesDao.delete(note)

    fun getAll(): Flow<List<Note>> = notesDao.getAll()

}