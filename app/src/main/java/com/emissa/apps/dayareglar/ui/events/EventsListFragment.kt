package com.emissa.apps.dayareglar.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.emissa.apps.dayareglar.R
import com.emissa.apps.dayareglar.databinding.FragmentEventsListBinding
import com.emissa.apps.dayareglar.ui.events.data.EventAdapter

class EventsListFragment : EventsBaseFragment() {
    private var _binding: FragmentEventsListBinding? = null
    private val binding get() = _binding!!
    // Instance of the adapter that will adapt the created event to the recyclerview
    // which will hold the list of events as a card view in a vertical linear layout
    private val eventAdapter by lazy {
        EventAdapter({
            val action = EventsListFragmentDirections.actionEventsListToEventDetails(it.id)
            this.findNavController().navigate(action)
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Configure the recyclerview for our list of events
        binding.eventRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = eventAdapter
        }
        // Attach an observer to the list of events to automatically update UI
        viewModel.allEvents.observe(viewLifecycleOwner) { events ->
            events.let { eventAdapter.updateEventsList(it) }
        }

        // Click on the fab add icon navigate user to screen to create a new event
        // Using the nav args of type 'string' here
        binding.fabAddEvent.setOnClickListener {
            val action = EventsListFragmentDirections.actionEventsListToEventsCreate(
                getString(R.string.add_event_title)
            )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}