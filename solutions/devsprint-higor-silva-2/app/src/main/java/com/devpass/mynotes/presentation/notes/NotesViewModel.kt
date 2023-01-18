package com.devpass.mynotes.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.mynotes.domain.exceptions.InvalidNoteException
import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.domain.usecase.NotesManagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val manager: NotesManagerUseCase
) : ViewModel() {

    private val errorMessage = MutableLiveData<String?>()
    fun observeErrorMessage(): MutableLiveData<String?> = errorMessage

    private val currentList = MutableLiveData<List<Note>>()
    fun observeCurrentList(): LiveData<List<Note>> = currentList

    var deletedNote = MutableLiveData<Note?>()

    init {
        getNotes()
    }

    fun insertNote(note: Note) =
        viewModelScope.launch {
            try {
                manager.add.invoke(note)
            } catch (e: InvalidNoteException) {
                errorMessage.value = e.message
            }
        }

    fun onSnackBarShown() {
        errorMessage.value = null
    }
    fun getNotes() =
        manager
            .getAll()
            .onEach { notes -> currentList.value = notes }
            .launchIn(viewModelScope)

    fun deleteNote(note: Note) {
        deletedNote.value = note

        viewModelScope.launch {
            manager.delete(note)
        }
    }

    fun undoDelete() {
        viewModelScope.launch {
            manager.add(deletedNote.value!!)
        }
        deletedNote.value = null
    }
}