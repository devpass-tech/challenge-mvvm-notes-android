package com.devpass.mynotes.presentation.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.domain.usecase.DeleteNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel(){

    var deletedNote = MutableLiveData<Note?>()

    fun deleteNote(note: Note){
        deletedNote.value = note

        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }

    fun undoDelete(){
        //add note novamente
        deletedNote.value = null
    }
}