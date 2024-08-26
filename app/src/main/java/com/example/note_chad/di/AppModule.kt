package com.example.note_chad.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.note_chad.repository.NotesRepository
import com.example.note_chad.roomdb.NotesDAO
import com.example.note_chad.roomdb.NotesDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext:Context): NotesDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            NotesDatabase::class.java,
            "NotesDatabase"
        ).fallbackToDestructiveMigration().build()
    }
    @Provides
    fun providesNoteDAO(db: NotesDatabase): NotesDAO {
        return db.notesdao()
    }
    @Provides
    @Singleton
    fun provideNotesRepository(dao: NotesDAO): NotesRepository {
        return NotesRepository(dao)
    }
    @Provides
    @Singleton
    fun providesFireBaseAuth():FirebaseAuth=FirebaseAuth.getInstance()
}