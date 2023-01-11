package com.devpass.mynotes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devpass.mynotes.domain.model.Note


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from note_table order by title ASC")
    fun getAllNotesOrderedByTitle(): LiveData<List<Note>>

    @Query("Select * from note_table order by time_stamp ASC")
    fun getAllNotesOrderedByDate(): LiveData<List<Note>>

    @Query("Select * from note_table order by color ASC")
    fun getAllNotesOrderedByColor(): LiveData<List<Note>>

}