package com.devpass.mynotes.presentation.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.devpass.mynotes.R
import com.devpass.mynotes.databinding.FragmentEditorBinding

//Esperando Yan concluir tarefa Create editor view
//para fazer checagem de nulabilidade e assim preencher a tela
class EditorFragment : Fragment(){

    private val args: EditorFragmentArgs by navArgs()
    
    private lateinit var binding: FragmentEditorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditorBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.purpleButton.setOnClickListener {
            context?.let {
                val color = ContextCompat.getColor(it, R.color.purple)
                binding.noteLayout.setBackgroundColor(color)
            }
        }
        binding.orangeButton.setOnClickListener {
            context?.let {
                val color = ContextCompat.getColor(it, R.color.orange)
                binding.noteLayout.setBackgroundColor(color)
            }
        }
        binding.blueButton.setOnClickListener {
            context?.let {
                val color = ContextCompat.getColor(it, R.color.blue)
                binding.noteLayout.setBackgroundColor(color)
            }
        }
        binding.greenButton.setOnClickListener {
            context?.let {
                val color = ContextCompat.getColor(it, R.color.teal)
                binding.noteLayout.setBackgroundColor(color)
            }
        }
        binding.yellowButton.setOnClickListener {
            context?.let {
                val color = ContextCompat.getColor(it, R.color.yellow)
                binding.noteLayout.setBackgroundColor(color)
            }
        }

        binding.confirmButton.setOnClickListener {
            // save note
        }
    }
}
