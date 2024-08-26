package com.example.note_chad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_chad.repository.NotesRepository
import com.example.note_chad.roomdb.NotesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val repository: NotesRepository):ViewModel(){
    val allNotes: LiveData<List<NotesEntity>> = repository.allNotes

    fun insert(note: NotesEntity)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    fun update(note: NotesEntity)=viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
    fun delete(note: NotesEntity)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
//    fun getAllNotes()=viewModelScope.launch(Dispatchers.IO) {
//        _allNotes= repository.allNotes as MutableLiveData<List<NotesEntity>>
//    }

    fun getNoteById(id:Int):LiveData<NotesEntity>{
        return repository.getNoteById(id)
    }


}