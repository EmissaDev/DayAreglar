package com.emissa.apps.dayareglar.ui.events.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class EventsViewModel (private val repository: EventsRepository) : ViewModel() {
    val allEvents: LiveData<List<Event>> = repository.getAllEvents().asLiveData()

    fun updateEvent(
        eventId: Int, eventTitle: String, eventCategory: String, eventDate: String
    ) {
        val eventToUpdate = getUpdatedEventDetails(eventId, eventTitle, eventCategory, eventDate)
        updateEvent(eventToUpdate)
    }

    fun addNewEvent(
        eventTitle: String,
        eventCategory: String,
        eventDate: String
    ) {
        val newEvent = getNewEventDetails(eventTitle, eventCategory, eventDate)
        insertEvent(newEvent)
    }

    fun retrieveEvent(id: Int): LiveData<Event> {
        return repository.getEvent(id).asLiveData()
    }

    /**
     * Do [insert, update, delete] operations without blocking the main thread
     */
    private fun insertEvent(event: Event) {
        viewModelScope.launch {
            repository.insertNewEvent(event)
        }
    }

    private fun updateEvent(event: Event) {
        viewModelScope.launch {
            repository.updateEvent(event)
        }
    }

    fun deleteEvent(event: Event) {
        viewModelScope.launch {
            repository.deleteEvent(event)
        }
    }

    private fun getNewEventDetails(
        eventTitle: String, eventCategory: String, eventDate: String
    ): Event {
        return Event(
            title = eventTitle,
            description = eventCategory,
            date = eventDate
        )
    }
    private fun getUpdatedEventDetails(
        eventId: Int,
        eventTitle: String,
        eventCategory: String,
        eventDate: String
    ): Event {
        return Event(
            id = eventId,
            title = eventTitle,
            description = eventCategory,
            date = eventDate
        )
    }
}

/**
 * This factory class returns an instance of the above View Model
 */
class EventsViewModelFactory(private val repository: EventsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class.")
    }

}