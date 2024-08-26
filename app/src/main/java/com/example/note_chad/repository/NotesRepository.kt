package com.example.note_chad.repository

import androidx.lifecycle.LiveData
import com.example.note_chad.roomdb.NotesDAO
import com.example.note_chad.roomdb.NotesEntity
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesdao: NotesDAO) {
    val allNotes: LiveData<List<NotesEntity>> = notesdao.getAllNotes()
    suspend fun insert(note: NotesEntity){
        notesdao.insert(note)
    }

    suspend fun update(note: NotesEntity){
        notesdao.update(note)
    }

    suspend fun delete(note: NotesEntity){
        notesdao.delete(note)
    }
    fun getNoteById(id:Int): LiveData<NotesEntity> {
       return notesdao.getNoteByID(id)
    }
}