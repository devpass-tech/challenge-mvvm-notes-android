package com.devpass.mynotes.data.repository

import androidx.lifecycle.LiveData
import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.data.NotesDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val notesDao: NotesDao) {

    val allNotesByTitle: LiveData<List<Note>> = notesDao.getAllNotesOrderedByTitle()
    val allNotesByDate: LiveData<List<Note>> = notesDao.getAllNotesOrderedByDate()
    val allNotesByColor: LiveData<List<Note>> = notesDao.getAllNotesOrderedByColor()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    fun getAll(): Flow<List<Note>> = notesDao.getAll()

}