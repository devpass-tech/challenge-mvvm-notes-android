package com.devpass.mynotes.domain.usecase

import com.devpass.mynotes.data.repository.NoteRepository
import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.domain.util.Filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(filter: Filter = Filter.Date): Flow<List<Note>> =
        repository.getAll().map { notes ->
            when (filter) {
                is Filter.Title -> notes.sortedBy { it.title.lowercase() }
                is Filter.Date -> notes.sortedBy { it.timeStamp }
                is Filter.Color -> notes.sortedBy { it.color }
            }
        }
}