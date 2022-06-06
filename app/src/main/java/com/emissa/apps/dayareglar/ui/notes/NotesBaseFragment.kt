package com.emissa.apps.dayareglar.ui.notes

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.emissa.apps.dayareglar.ui.notes.data.NotesDatabase
import com.emissa.apps.dayareglar.ui.notes.data.NotesRepository
import com.emissa.apps.dayareglar.ui.notes.data.NotesViewModel
import com.emissa.apps.dayareglar.ui.notes.data.NotesViewModelFactory

open class NotesBaseFragment : Fragment() {
    lateinit var viewModel: NotesViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)

        val repository = activity?.applicationContext?.let {
            NotesDatabase.getDatabase(
                it
            ).noteDao()
        }?.let { NotesRepository(it) }

        val factory = repository?.let { NotesViewModelFactory(it) }

        viewModel = factory?.let {
            ViewModelProvider(requireActivity(), it)
        }?.get(NotesViewModel::class.java)!!
    }
}