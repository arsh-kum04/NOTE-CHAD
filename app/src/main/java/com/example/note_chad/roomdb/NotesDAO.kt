package com.example.note_chad.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDAO {

    @Insert
    suspend fun insert(note: NotesEntity)

    @Update
    suspend fun update(note: NotesEntity)

    @Delete
    suspend fun delete(note: NotesEntity)

    @Query("SELECT * FROM `Notes` ORDER BY timestamp DESC")
    fun getAllNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM `Notes` WHERE id = :id ")
    fun getNoteByID(id:Int):LiveData<NotesEntity>
}