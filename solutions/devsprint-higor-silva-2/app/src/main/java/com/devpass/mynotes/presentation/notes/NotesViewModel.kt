package com.devpass.mynotes.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.mynotes.domain.exceptions.InvalidNoteException
import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.domain.usecase.AddNoteUseCase
import com.devpass.mynotes.domain.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel(){

    var deletedNote = MutableLiveData<Note>()
    private val _snackBar = MutableLiveData<String?>()
    val snackbar: LiveData<String?>
        get() = _snackBar


    fun insertNote(note: Note){
        viewModelScope.launch {
            try {
                noteUseCases.addNote.invoke(note)
            } catch (e: InvalidNoteException){
                _snackBar.value = e.message
            }
        }
    }

    fun onSnackBarShown(){
        _snackBar.value = null
    }

}