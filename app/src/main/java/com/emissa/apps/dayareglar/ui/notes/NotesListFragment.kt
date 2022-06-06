package com.emissa.apps.dayareglar.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.emissa.apps.dayareglar.R
import com.emissa.apps.dayareglar.databinding.FragmentNotesListBinding
import com.emissa.apps.dayareglar.interfaces.ClickListeners
import com.emissa.apps.dayareglar.ui.notes.data.Note
import com.emissa.apps.dayareglar.ui.notes.data.NoteAdapter

class NotesListFragment : NotesBaseFragment(), ClickListeners {

    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!
    private val noteAdapter by lazy {
        NoteAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set recyclerview and its adapter to display notes
        binding.noteRecyclerview.apply {
            setHasFixedSize(false)
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            adapter = noteAdapter
        }

        viewModel.allNotes.observe(viewLifecycleOwner) { notes ->
            notes.let {
                noteAdapter.submitList(it)
            }
        }

        // Navigate to screen to 'Add a new note'
        binding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_NotesList_to_NotesCreate)
        }

    }

    // Navigate to 'Details screen' when a click occurs on an item of the recyclerview
    override fun <T> onItemClicked(item: T) {
        viewModel.selectedNote = (item as Note)
        findNavController().navigate(R.id.action_NotesList_to_NoteDetails)
    }

    // Delete note from list and remove it from the layout when a long click occurs
    override fun <T> onItemLongClicked(item: T): Boolean {
        viewModel.deleteNote(item as Note)
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}