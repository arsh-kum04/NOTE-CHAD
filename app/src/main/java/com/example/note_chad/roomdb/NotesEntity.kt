package com.example.note_chad.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class NotesEntity(

    @PrimaryKey(autoGenerate = true)val id: Int=0,
    val title:String,
    val content:String,
    val timestamp: Long

)
