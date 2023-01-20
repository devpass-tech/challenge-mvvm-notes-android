package com.devpass.mynotes.presentation.notes

import androidx.lifecycle.*
import com.devpass.mynotes.domain.exceptions.InvalidNoteException
import com.devpass.mynotes.R
import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.domain.usecase.NotesManagerUseCase
import com.devpass.mynotes.domain.util.Filter
import com.devpass.mynotes.domain.util.SelfCleaningLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.time.Instant
import java.util.*

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val manager: NotesManagerUseCase
) : ViewModel() {

    private val errorMessage = MutableLiveData<String?>()
    fun observeErrorMessage(): MutableLiveData<String?> = errorMessage

    private val currentList = MutableLiveData<List<Note>>()
    fun observeCurrentList(): LiveData<List<Note>> = currentList

    var deletedNote = SelfCleaningLiveData<Note?>()

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
    fun getNotes(filter: Filter) =
        manager
            .getAll(filter)
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
