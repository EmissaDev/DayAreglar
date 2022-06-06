package com.emissa.apps.dayareglar.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.emissa.apps.dayareglar.databinding.FragmentNoteDetailsBinding
import com.emissa.apps.dayareglar.ui.notes.data.Note
import java.util.*

class NoteDetailsFragment : NotesBaseFragment() {

    private var _binding: FragmentNoteDetailsBinding? =  null
    private val binding get() = _binding!!
    private lateinit var note: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteDetailsBinding.inflate(inflater, container, false)
        note = viewModel.selectedNote!!
        if (note != null) {
            viewModel.getNote(note.id).observe(viewLifecycleOwner) {
                bindNote(it)
            }
        }

        return binding.root
    }

    private fun bindNote(note: Note) {
        binding.apply {
            tfNoteTitle.setText(note.noteTitle, TextView.BufferType.EDITABLE)
            etNoteContent.setText(note.noteContent, TextView.BufferType.EDITABLE)
        }
    }

    private fun saveEditedNote() {
        val title = binding.tfNoteTitle.text.toString()
        val content = binding.etNoteContent.text.toString()
        val time = Calendar.getInstance().time

        if (!title.isNullOrEmpty() && !content.isNullOrEmpty()) {
            viewModel.updateNote(note.id, title, content, time.toString())
        } else if (title.isNullOrEmpty() && !content.isNullOrEmpty()) {
            // Get the first word of note content and set it as title
            val tempTitle: String = content.trim().substring(0)
            viewModel.updateNote(note.id, tempTitle, content, time.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveEditedNote()
        _binding = null
    }
}