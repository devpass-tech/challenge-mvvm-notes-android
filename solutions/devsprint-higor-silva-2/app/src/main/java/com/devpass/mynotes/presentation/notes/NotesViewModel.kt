package com.devpass.mynotes.presentation.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.domain.usecase.NotesManagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val manager: NotesManagerUseCase
) : ViewModel() {


    var deletedNote = MutableLiveData<Note?>()

    private val currentList = MutableLiveData<List<Note>>()
    fun observeCurrentList(): LiveData<List<Note>> = currentList

    init {
        insertNote()
        getNotes()
    }

    private fun insertNote() = viewModelScope.launch {
        val note = Note(
            id = 123,
            title = "Nova nota",
            content = "klsdkaslkdlkas",
            color = 0,
            timeStamp = 0,
        )
        manager.add(note = note)
    }

    fun getNotes() = manager.getAll().onEach { notes ->
        currentList.value = notes

    }.launchIn(viewModelScope)


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
