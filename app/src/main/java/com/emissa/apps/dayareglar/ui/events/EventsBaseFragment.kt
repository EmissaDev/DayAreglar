package com.emissa.apps.dayareglar.ui.events

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.emissa.apps.dayareglar.ui.events.data.EventsDatabase
import com.emissa.apps.dayareglar.ui.events.data.EventsRepository
import com.emissa.apps.dayareglar.ui.events.data.EventsViewModel
import com.emissa.apps.dayareglar.ui.events.data.EventsViewModelFactory

open class EventsBaseFragment : Fragment() {
    lateinit var viewModel: EventsViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)

        val repository = activity?.applicationContext?.let {
            EventsDatabase.getDatabase(
                it
            ).eventDao()
        }?.let { EventsRepository(it) }

        val factory = repository?.let { EventsViewModelFactory(it) }

        viewModel = factory?.let {
            ViewModelProvider(requireActivity(), it)
        }?.get(EventsViewModel::class.java)!!
    }
}