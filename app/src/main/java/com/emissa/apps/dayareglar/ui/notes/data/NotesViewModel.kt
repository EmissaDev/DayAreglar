package com.emissa.apps.dayareglar.ui.notes.data

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NotesViewModel(private val repository: NotesRepository): ViewModel() {
    val allNotes: LiveData<List<Note>> = repository.getAllNotes().asLiveData()
    var selectedNote: Note? = null

    fun addNewNote(
        ntTitle: String, content: String, ntDate: String
    ) {
        val newNote = getNewNoteDetails(ntTitle, content, ntDate)
        insertNewNote(newNote)
    }

    fun updateNote(
        ntID: Long, ntTitle: String, content: String, ntDate: String
    ) {
        val noteToUpdate = getUpdatedNoteDetails(ntID, ntTitle, content, ntDate)
        updateNote(noteToUpdate)
    }

    fun getNote(id: Long): LiveData<Note> {
        return repository.getNote(id).asLiveData()
    }


    /**
     * Do [insert, update, delete] operations without blocking the main thread
     */
    private fun insertNewNote(note: Note) {
        viewModelScope.launch {
            repository.insertNewNote(note)
        }
    }
    private fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    private fun getNewNoteDetails(
        ntTitle: String, content: String, ntDate: String
    ): Note {
        return Note(
            noteTitle = ntTitle,
            noteContent = content,
            timeStamp = ntDate
        )
    }

    private fun getUpdatedNoteDetails(
        ntID: Long, ntTitle: String, content: String, ntDate: String
    ): Note {
        return Note(
            id = ntID,
            noteTitle = ntTitle,
            noteContent = content,
            timeStamp = ntDate
        )
    }
}

class NotesViewModelFactory(
    private val repository: NotesRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class.")
    }
}