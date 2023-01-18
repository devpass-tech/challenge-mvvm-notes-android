package com.devpass.mynotes.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devpass.mynotes.R
import com.devpass.mynotes.databinding.FragmentNotesBinding
import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.presentation.adapter.NotesListAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.util.*

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotesListAdapter

    private lateinit var layoutFilter: LinearLayout
    private lateinit var btnFilter: ImageButton

    private val viewModel: NotesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.observeCurrentList().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNotesBinding.inflate(inflater, container, false)

        setupRecyclerView(binding)
        setupFilter(binding)

        viewModel.getNotes()
        return binding.root
    }

    private fun setupFilter(binding: FragmentNotesBinding) {
        layoutFilter = binding.layoutFilter
        btnFilter = binding.btnFilter
        btnFilter.setOnClickListener { toggleVisibility(layoutFilter) }
    }

    private fun setupRecyclerView(binding: FragmentNotesBinding) {
        adapter = NotesListAdapter(::onNoteClicked, ::showUndoSnackBar, requireContext())
        recyclerView = binding.rvListNotes
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE)
            View.GONE else View.VISIBLE
    }

    private fun onNoteClicked(noteClicked: Note) {
        val action =
            NotesFragmentDirections.actionNotesFragmentToEditorFragment2(noteClicked)

        findNavController().navigate(action)
    }

    private fun showUndoSnackBar() {
        Snackbar.make(
            requireView(),
            R.string.message_note_deleted,
            Snackbar.LENGTH_LONG
        ).setAction(R.string.message_undo_delete) {

        }.show()
    }
}
