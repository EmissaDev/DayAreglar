package com.emissa.apps.dayareglar.ui.events.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    /**
     * Returns all saved events, ordered by id (the last one inserted shows first)
     */
    @Query("SELECT * from event ORDER BY event_id DESC")
    fun getAllEvents(): Flow<List<Event>>

    @Query("SELECT * from event WHERE event_id = :id")
    fun getEvent(id: Int): Flow<Event>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewEvent(event: Event)
    @Update
    suspend fun updateEvent(event: Event)
    @Delete
    suspend fun deleteEvent(event: Event)
}