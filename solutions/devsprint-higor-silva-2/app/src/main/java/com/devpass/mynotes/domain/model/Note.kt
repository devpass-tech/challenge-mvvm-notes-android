package com.devpass.mynotes.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devpass.mynotes.R

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "color") val color: Int = R.color.yellow,
    @ColumnInfo(name = "time_stamp") val timeStamp: Long
) {
    companion object {
        val noteColors = listOf(
            R.color.yellow, R.color.purple, R.color.orange, R.color.blue, R.color.teal
        )
    }
}
