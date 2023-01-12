package com.devpass.mynotes.domain.usecase


import com.devpass.mynotes.data.repository.NoteRepository
import com.devpass.mynotes.domain.exceptions.InvalidNoteException
import com.devpass.mynotes.domain.model.Note

class AddNoteUseCase(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("O título da anotação não pode estar vazio")
        }

        if (note.content.isBlank()) {
            throw InvalidNoteException("O conteúdo da anotação não pode estar vazio")
        }

        noteRepository.insert(note)
    }
}