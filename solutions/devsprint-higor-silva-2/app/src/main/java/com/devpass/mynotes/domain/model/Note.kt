package com.devpass.mynotes.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "title") val title:String?,
    @ColumnInfo(name = "content") val content:String?,
    @ColumnInfo(name = "color") val color:Int,
    @ColumnInfo(name = "time_stamp") val timeStamp:Long
    )
