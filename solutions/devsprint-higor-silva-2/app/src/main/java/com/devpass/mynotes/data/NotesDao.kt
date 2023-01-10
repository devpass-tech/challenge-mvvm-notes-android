package com.devpass.mynotes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.devpass.mynotes.domain.model.Note


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from note_table order by title ASC")
    fun getAllNotesOrderedByTitle(): LiveData<List<Note>>

    @Query("Select * from note_table order by time_stamp ASC")
    fun getAllNotesOrderedByDate(): LiveData<List<Note>>

    @Query("Select * from note_table order by color ASC")
    fun getAllNotesOrderedByColor(): LiveData<List<Note>>

    @Update
    suspend fun update(note: Note)

}