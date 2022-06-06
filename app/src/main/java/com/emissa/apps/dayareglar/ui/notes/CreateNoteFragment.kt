package com.emissa.apps.dayareglar.ui.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emissa.apps.dayareglar.databinding.FragmentCreateNoteBinding
import java.util.*

class CreateNoteFragment : NotesBaseFragment() {

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            addNoteTitle
        }
    }

    private fun addNewNote() {
        val title = binding.addNoteTitle.text.toString()
        val content = binding.addNoteContent.text.toString()
        val time = Calendar.getInstance().time
        Log.d("NoteCreate", "addNewNote: time value = ${time.toString()}")

        if (!title.isNullOrEmpty() && !content.isNullOrEmpty()) {
            viewModel.addNewNote(title, content, time.toString())
        } else if (title.isNullOrEmpty() && !content.isNullOrEmpty()) {
            // Get the first word of note content and set it as title
            val tempTitle: String = content.trim().substring(0)
            viewModel.addNewNote(tempTitle, content, time.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // note is saved without user's action
        // once the 'go back arrow' is pressed, note is saved
        addNewNote()
        _binding = null
    }
}