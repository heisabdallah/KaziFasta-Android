package com.example.kazifasta.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
)
