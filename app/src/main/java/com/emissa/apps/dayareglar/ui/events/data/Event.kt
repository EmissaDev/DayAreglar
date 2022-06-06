package com.emissa.apps.dayareglar.ui.events.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "event_id")
    val id: Int = 0,

    @ColumnInfo(name = "event_title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "event_date")
    val date: String
)