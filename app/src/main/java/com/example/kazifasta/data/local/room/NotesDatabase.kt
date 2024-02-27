package com.example.kazifasta.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteModel::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

}
