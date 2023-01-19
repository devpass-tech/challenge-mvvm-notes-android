package com.devpass.mynotes.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devpass.mynotes.R
import com.devpass.mynotes.databinding.ItemNoteBinding
import com.devpass.mynotes.domain.model.Note
import java.text.SimpleDateFormat

class NotesListAdapter(
    private val edit: (Note) -> Unit,
    private val delete: (Note) -> Unit
) :
    ListAdapter<Note, NotesListAdapter.NotesViewHolder>(NotesListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.apply {
            val note = getItem(position)
            bind(note)

            editButton.setOnClickListener {
                edit(note)
            }

            deleteButton.setOnClickListener {
                delete(note)
            }
        }
    }

    inner class NotesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)

        lateinit var editButton: ImageButton
        lateinit var deleteButton: ImageButton

        @SuppressLint("SimpleDateFormat")
        fun bind(data: Note) =
            binding.apply {
                txtTitleNote.text = data.title
                txtContentNote.text = data.content

                val formatted = SimpleDateFormat("yyyy.MM.dd HH:mm")
                txtDateNote.text = formatted.format(data.timeStamp)

                editButton = imgEdit
                deleteButton = imgDelete
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
