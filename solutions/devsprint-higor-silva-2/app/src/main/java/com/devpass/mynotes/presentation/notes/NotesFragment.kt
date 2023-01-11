package com.devpass.mynotes.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.devpass.mynotes.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

    private lateinit var layoutFilter: LinearLayout
    private lateinit var btnFilter: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNotesBinding.inflate(inflater, container, false)

        layoutFilter = binding.layoutFilter
        btnFilter = binding.btnFilter

        btnFilter.setOnClickListener { toggleVisibility(layoutFilter) }

        return binding.root
    }

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE)
            View.GONE else View.VISIBLE
    }

}