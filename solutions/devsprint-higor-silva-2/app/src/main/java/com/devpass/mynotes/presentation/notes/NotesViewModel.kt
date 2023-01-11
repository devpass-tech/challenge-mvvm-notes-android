package com.devpass.mynotes.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devpass.mynotes.domain.model.NoteModel

class NotesViewModel : ViewModel(){

    var deletedNote = MutableLiveData<NoteModel>()

}