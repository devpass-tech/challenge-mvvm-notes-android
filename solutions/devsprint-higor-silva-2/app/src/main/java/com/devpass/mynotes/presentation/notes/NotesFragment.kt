package com.devpass.mynotes.presentation.notes

import androidx.fragment.app.Fragment
import com.devpass.mynotes.R
import com.google.android.material.snackbar.Snackbar


class NotesFragment : Fragment() {

    private fun showUndoSnackBar() {

        Snackbar.make(
            requireView(),
            R.string.message_note_deleted,
            Snackbar.LENGTH_SHORT
        ).setAction(R.string.message_undo_delete) {

        }.show()
    }
}