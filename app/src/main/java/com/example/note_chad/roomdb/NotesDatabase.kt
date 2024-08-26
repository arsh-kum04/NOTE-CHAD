package com.example.note_chad.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase:RoomDatabase() {
    abstract fun notesdao(): NotesDAO
    }