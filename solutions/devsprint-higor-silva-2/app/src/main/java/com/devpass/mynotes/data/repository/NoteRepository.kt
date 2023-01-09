package com.devpass.mynotes.data.repository

import androidx.lifecycle.LiveData
import com.devpass.mynotes.data.Note
import com.devpass.mynotes.data.NotesDao

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

    suspend fun update(note:Note) {
        notesDao.update(note)
    }
}