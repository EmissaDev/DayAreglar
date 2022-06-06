package com.emissa.apps.dayareglar.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.emissa.apps.dayareglar.databinding.FragmentCreateEventBinding
import com.emissa.apps.dayareglar.ui.events.data.Event
import java.util.*

class CreateEventFragment : EventsBaseFragment() {
    private var _binding: FragmentCreateEventBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: EventDetailsFragmentArgs by navArgs()
    private lateinit var selectedDate: String
    lateinit var event: Event

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateEventBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set events date based on user's selection or the actual date
        // need to work on  condition statement to handle this efficiently
        binding.calendarView.setOnDateChangeListener { _, year, month, day ->
            val date = Calendar.getInstance()
            date.set(year, month, day)
            selectedDate = date.time.toString()
        }

        val id = navigationArgs.eventId
        if (id > 0) {
            viewModel.retrieveEvent(id).observe(this.viewLifecycleOwner) { currentEvent ->
                event = currentEvent
                bindEvent(event)
            }
        } else {
            binding.btnAddEvent.setOnClickListener {
                addNewEvent()
            }
        }
    }

    private fun bindEvent(event: Event) {
        selectedDate = event.date
        binding.apply {
            etEventTitle.setText(event.title)
            etEventDescription.setText(event.description)
        }
    }

    private fun addNewEvent() {
        viewModel.addNewEvent(
            binding.etEventTitle.text.toString(),
            binding.etEventDescription.text.toString(),
            selectedDate
        )
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}