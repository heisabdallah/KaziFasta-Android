package com.example.kazifasta.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    // Constructor (no implementation needed for interfaces)

    @Insert
    suspend fun insertNote(note: NoteModel)

    @Query("SELECT * FROM notemodel")
    fun getAllNotes(): Flow<List<NoteModel>>

    @Query("DELETE FROM notemodel WHERE id = :id")
    suspend fun deleteNote(id: Int)
}