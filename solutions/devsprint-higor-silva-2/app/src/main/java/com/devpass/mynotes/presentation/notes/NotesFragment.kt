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
import com.devpass.mynotes.domain.util.Filter
import com.devpass.mynotes.presentation.adapter.NotesListAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private val viewModel: NotesViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotesListAdapter

    private lateinit var layoutFilter: LinearLayout
    private lateinit var btnFilter: ImageButton

    private var selectedFilter: Filter = Filter.Title

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.observeCurrentList().observe(viewLifecycleOwner) {
            adapter.submitList(it)

            recyclerView.post {
                recyclerView.scrollToPosition(0)
            }
        }

        viewModel.deletedNote.observe(viewLifecycleOwner) {
            if (it != null) {
                showUndoSnackBar()
            }
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
        setupHeader(binding)

        val pref = requireActivity().getSharedPreferences("filter", 0)
        val editor = pref.edit()

        binding.groupFilter.setOnCheckedChangeListener { radioGroup, _ ->

            when (radioGroup.checkedRadioButtonId) {
                R.id.sortByTitle -> {
                    selectedFilter = Filter.Title
                    viewModel.getNotes(selectedFilter)
                    editor.putInt("filter", 0)
                }
                R.id.sortByDate -> {
                    selectedFilter = Filter.Date
                    viewModel.getNotes(selectedFilter)
                    editor.putInt("filter", 1)
                }
                R.id.sortByColor -> {
                    selectedFilter = Filter.Color
                    viewModel.getNotes(selectedFilter)
                    editor.putInt("filter", 2)
                }
            }

            editor.apply()
        }

        when(pref.getInt("filter", 0)){
            0 -> {
                binding.sortByTitle.isChecked = true
                viewModel.getNotes(Filter.Title)
            }
            1 -> {
                binding.sortByDate.isChecked = true
                viewModel.getNotes(Filter.Date)
            }
            2 -> {
                binding.sortByColor.isChecked = true
                viewModel.getNotes(Filter.Color)
            }
        }

        return binding.root
    }

    private fun setupHeader(binding: FragmentNotesBinding) {
        layoutFilter = binding.layoutFilter
        btnFilter = binding.btnFilter
        btnFilter.setOnClickListener { toggleVisibility(layoutFilter) }
        binding.btnAddNote.setOnClickListener { onNoteClicked(null) }
    }

    private fun setupRecyclerView(binding: FragmentNotesBinding) {
        adapter = NotesListAdapter(::onNoteClicked, ::onDeleteButtonClicked, requireContext())
        recyclerView = binding.rvListNotes
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE)
            View.GONE else View.VISIBLE
    }

    private fun onNoteClicked(noteClicked: Note?) {
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
            viewModel.undoDelete()
            viewModel.getNotes(selectedFilter)
        }.show()
    }

    private fun onDeleteButtonClicked(noteDeleted: Note) {
        viewModel.deleteNote(noteDeleted)
        viewModel.getNotes(selectedFilter)
    }
}
