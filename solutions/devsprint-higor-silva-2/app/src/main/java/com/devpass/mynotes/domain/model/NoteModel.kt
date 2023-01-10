package com.devpass.mynotes.domain.model

import java.sql.Timestamp

data class NoteModel(
    val id: String,
    val title:String,
    val content: String,
    val color: Int,
    val timestamp: Timestamp
)
