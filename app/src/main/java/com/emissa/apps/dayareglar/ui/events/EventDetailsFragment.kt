package com.emissa.apps.dayareglar.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.emissa.apps.dayareglar.databinding.FragmentEventDetailsBinding
import com.emissa.apps.dayareglar.ui.events.data.Event
import com.emissa.apps.dayareglar.utils.DATE_FORMAT
import java.util.*

class EventDetailsFragment : EventsBaseFragment() {
    private var _binding: FragmentEventDetailsBinding? = null
    private val binding get() = _binding!!
    private val navigationArgs: EventDetailsFragmentArgs by navArgs()
    private lateinit var eventDate: String
    lateinit var event: Event


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventDetailsBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.eventId
        viewModel.retrieveEvent(id).observe(viewLifecycleOwner) { selectedEvent ->
            event = selectedEvent
            bindEvent(event)
        }
        // listen to changes that might occur on the calendar view to get new date fot this event
        binding.calendarView.setOnDateChangeListener { _, year, month, day ->
            val date = Calendar.getInstance()
            date.set(year, month, day)
            //set date selection as new date to be saved for this event
            eventDate = date.time.toString()
        }
    }

    private fun bindEvent(event: Event) {
        //set value of the date to be used if User does not change date
        eventDate = event.date
        binding.apply {
            eventTitle.setText(event.title, TextView.BufferType.EDITABLE)
            eventDescription.setText(event.description, TextView.BufferType.EDITABLE)
            calendarView.date = setDateOnCalendarView(event)
        }
    }

    // save displayed event with same id, but modified values for title, details and date
    private fun saveEditedEvent() {
        viewModel.updateEvent(
            this.navigationArgs.eventId,
            binding.eventTitle.text.toString(),
            binding.eventDescription.text.toString(),
            eventDate
        )
    }

    // Set the day this event is scheduled to on the CalendarView
    private fun setDateOnCalendarView(event: Event) : Long {
        DATE_FORMAT.isLenient = true
        val date = DATE_FORMAT.parse(event.date)
        return date.time
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveEditedEvent()
        _binding = null
    }
}