package com.emissa.apps.dayareglar.ui.notes.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note ORDER BY note_date DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE note_id = :id")
    fun getNote(id: Long): Flow<Note>

    @Query("SELECT * FROM note WHERE note_title LIKE :title")
    fun findNoteByTitle(title: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewNote(note: Note)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<Note>)

    @Update
    suspend fun updateNote(note: Note)
    @Delete
    suspend fun deleteNote(note: Note)
}