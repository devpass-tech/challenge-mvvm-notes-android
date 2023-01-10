package com.devpass.mynotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devpass.mynotes.domain.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDataBase: RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

}