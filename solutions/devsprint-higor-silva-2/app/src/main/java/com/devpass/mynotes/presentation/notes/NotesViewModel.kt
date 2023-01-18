package com.devpass.mynotes.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.mynotes.R
import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.domain.usecase.NotesManagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.*

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val manager: NotesManagerUseCase
) : ViewModel() {

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
            color = R.color.yellow,
            timeStamp = Date.from(Instant.now()).time,
        )
        manager.add(note = note)
    }

    fun getNotes() = manager.getAll().onEach { notes ->
        currentList.value = notes

    }.launchIn(viewModelScope)


}