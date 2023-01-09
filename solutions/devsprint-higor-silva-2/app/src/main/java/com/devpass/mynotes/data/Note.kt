package com.devpass.mynotes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.devpass.mynotes.utils.TimeStampConverter


@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "title") val title:String?,
    @ColumnInfo(name = "content") val content:String?,
    @ColumnInfo(name = "color") val color:String?,
    @ColumnInfo(name = "time_stamp") @TypeConverters(TimeStampConverter::class) val timestamp:String?,

    )
