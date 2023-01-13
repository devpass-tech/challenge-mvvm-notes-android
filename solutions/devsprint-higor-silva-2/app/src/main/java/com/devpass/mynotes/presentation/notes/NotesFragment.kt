package com.devpass.mynotes.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devpass.mynotes.R
import com.devpass.mynotes.databinding.FragmentNotesBinding
import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.presentation.adapter.NotesListAdapter
import java.time.Instant
import java.util.*

class NotesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotesListAdapter

    private lateinit var layoutFilter: LinearLayout
    private lateinit var btnFilter: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNotesBinding.inflate(inflater, container, false)

        adapter = NotesListAdapter(::onNoteClicked)
        recyclerView = binding.rvListNotes

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        initializeDummyList()

        layoutFilter = binding.layoutFilter
        btnFilter = binding.btnFilter

        btnFilter.setOnClickListener { toggleVisibility(layoutFilter) }

        return binding.root
    }

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE)
            View.GONE else View.VISIBLE
    }

    private fun onNoteClicked(noteClicked: Note) {
        val action =
            NotesFragmentDirections.actionNotesFragmentToEditorFragment2()

        findNavController().navigate(action)
    }

    private fun initializeDummyList() {
        val dummyList = listOf(
            Note(1, "Nota1", "Nota 1 do Higor", R.color.yellow, Date.from(Instant.now()).time),
            Note(2, "Nota2", "Nota 1 do Higor", R.color.purple, Date.from(Instant.now()).time),
            Note(3, "Nota3", "Nota 1 do Higor", R.color.blue, Date.from(Instant.now()).time),
            Note(4, "Nota4", "Nota 1 do Higor", R.color.blue, Date.from(Instant.now()).time)
        )

        adapter.submitList(dummyList)
    }
}
