package com.devpass.mynotes.domain.usecase

data class NotesManagerUseCase(
    val add: AddNoteUseCase,
    val getAll: GetNotesUseCase,
    val delete: DeleteNoteUseCase
)