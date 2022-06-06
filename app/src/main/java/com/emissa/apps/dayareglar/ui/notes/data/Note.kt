package com.emissa.apps.dayareglar.ui.notes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val id: Long = 0L,

    @ColumnInfo(name = "note_title")
    var noteTitle: String,

    @ColumnInfo(name = "note_content")
    var noteContent: String,

    @ColumnInfo(name = "note_date")
    var timeStamp: String
)