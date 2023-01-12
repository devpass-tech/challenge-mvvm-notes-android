package com.devpass.mynotes.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devpass.mynotes.R
import com.devpass.mynotes.databinding.ItemNoteBinding
import com.devpass.mynotes.domain.model.Note

class NotesListAdapter(private val click: (Note) -> Unit) :
    ListAdapter<Note, NotesListAdapter.NotesViewHolder>(NotesListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        holder.apply {
            val note = getItem(position)

            bind(note)

            noteLayout.setOnClickListener {
                click(note)
            }

        }
    }

    inner class NotesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)

        lateinit var noteLayout: ConstraintLayout
        lateinit var editButton: Button
        lateinit var deleteButton: Button

        fun bind(data: Note) {

            binding.apply {
                txtTitleNote.text = data.title
                txtContentNote.text = data.content

                noteLayout = noteItem
            }
        }
    }

    private companion object : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}