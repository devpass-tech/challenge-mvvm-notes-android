package com.devpass.mynotes.presentation.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devpass.mynotes.R
import com.devpass.mynotes.databinding.FragmentEditorBinding
import com.devpass.mynotes.presentation.notes.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditorFragment : Fragment() {

    private lateinit var binding: FragmentEditorBinding
    val viewModel: NotesViewModel by viewModels()
    var color = R.color.yellow

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditorBinding.inflate(inflater, container, false)

        viewModel.snackbar.observe(viewLifecycleOwner) { snackBar ->
            snackBar?.let {
                Toast.makeText(requireContext(), snackBar, Toast.LENGTH_SHORT).show()
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
        binding.purpleButton.setOnClickListener {
            context?.let {
                color = ContextCompat.getColor(it, R.color.purple)
                binding.noteLayout.setBackgroundColor(color)
            }
        }
        binding.orangeButton.setOnClickListener {
            context?.let {
                color = ContextCompat.getColor(it, R.color.orange)
                binding.noteLayout.setBackgroundColor(color)
            }
        }
        binding.blueButton.setOnClickListener {
            context?.let {
                color = ContextCompat.getColor(it, R.color.blue)
                binding.noteLayout.setBackgroundColor(color)
            }
        }
        binding.greenButton.setOnClickListener {
            context?.let {
                color = ContextCompat.getColor(it, R.color.teal)
                binding.noteLayout.setBackgroundColor(color)
            }
        }
        binding.yellowButton.setOnClickListener {
            context?.let {
                color = ContextCompat.getColor(it, R.color.yellow)
                binding.noteLayout.setBackgroundColor(color)
            }
        }

        binding.confirmButton.setOnClickListener {
//            viewModel.insertNote(
//                Note()
//            )
        }
    }
}