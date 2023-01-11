package com.devpass.mynotes.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devpass.mynotes.databinding.FragmentNotesBinding
import com.devpass.mynotes.domain.model.NoteModel

class NotesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            FragmentNotesBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun onNoteClicked(noteClicked: NoteModel){
        val action =
            NotesFragmentDirections.actionNotesFragmentToEditorFragment2()

        findNavController().navigate(action)
    }
}