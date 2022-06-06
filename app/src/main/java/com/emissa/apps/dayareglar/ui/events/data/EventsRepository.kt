package com.emissa.apps.dayareglar.ui.events.data

import kotlinx.coroutines.flow.Flow

class EventsRepository(private val dao: EventDao) {
    fun getAllEvents(): Flow<List<Event>> = dao.getAllEvents()
    fun getEvent(id: Int): Flow<Event> = dao.getEvent(id)

    suspend fun insertNewEvent(event: Event) {
        return dao.insertNewEvent(event)
    }
    suspend fun updateEvent(event: Event) {
        return dao.updateEvent(event)
    }
    suspend fun deleteEvent(event: Event) {
        return dao.deleteEvent(event)
    }
}