package com.emissa.apps.dayareglar.ui.notes.data

import kotlinx.coroutines.flow.Flow

class NotesRepository(private val dao: NoteDao) {
    fun getAllNotes(): Flow<List<Note>> = dao.getAllNotes()
    fun getNote(id: Long): Flow<Note> = dao.getNote(id)
    fun findNoteByTitle(title: String): Note = dao.findNoteByTitle(title)

    suspend fun insertNewNote(note: Note) {
        return dao.insertNewNote(note)
    }
    suspend fun insertAll(notes: List<Note>) {
        return dao.insertAll(notes)
    }
    suspend fun updateNote(note: Note) {
        return dao.updateNote(note)
    }
    suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }
}