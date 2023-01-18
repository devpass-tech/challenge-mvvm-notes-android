package com.devpass.mynotes.presentation.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devpass.mynotes.R
import com.devpass.mynotes.databinding.FragmentEditorBinding
import com.devpass.mynotes.domain.model.Note
import com.devpass.mynotes.presentation.notes.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditorFragment : Fragment() {

    private lateinit var binding: FragmentEditorBinding
    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var noteLayout: LinearLayout

    private val args: EditorFragmentArgs by navArgs()
    private val viewModel: NotesViewModel by viewModels()

    private var currentColorId: Int = R.color.yellow
    private var noteId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditorBinding
            .inflate(inflater, container, false)

        titleEditText = binding.titleEditText
        contentEditText = binding.contentEditText
        noteLayout = binding.noteLayout

        args.noteClicked?.let { note ->
            titleEditText.setText(note.title)
            contentEditText.setText(note.content)
            currentColorId = note.color
            binding.noteLayout.setBackgroundColor(requireContext().getColor(currentColorId))
            noteId = note.id
        }

        viewModel.observeErrorMessage().observe(viewLifecycleOwner) { message ->
            message?.let {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                viewModel.onSnackBarShown()
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.apply {
            purpleButton.setOnClickListener(getColorChangeListener(R.color.purple))
            orangeButton.setOnClickListener(getColorChangeListener(R.color.orange))
            blueButton.setOnClickListener(getColorChangeListener(R.color.blue))
            greenButton.setOnClickListener(getColorChangeListener(R.color.teal))
            yellowButton.setOnClickListener(getColorChangeListener(R.color.yellow))
        }

        binding.confirmButton.setOnClickListener {
            val note = Note(
                title = binding.titleEditText.text.toString(),
                content = binding.contentEditText.text.toString(),
                color = currentColorId,
                timeStamp = System.currentTimeMillis(),
                id = noteId
            )

            viewModel.insertNote(note)
            findNavController().popBackStack()
        }
    }
    private fun getColorChangeListener(colorId: Int): OnClickListener =
        OnClickListener {
            currentColorId = colorId
            val color = requireContext().getColor(currentColorId)
            binding.noteLayout.setBackgroundColor(color)
        }

}

