package com.devpass.mynotes.domain.usecase

import com.devpass.mynotes.data.repository.NoteRepository
import com.devpass.mynotes.domain.model.Note

class DeleteNoteUseCase(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: Note){
        noteRepository.delete(note)
    }
}