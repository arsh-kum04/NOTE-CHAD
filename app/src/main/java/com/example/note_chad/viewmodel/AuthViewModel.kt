package com.example.note_chad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_chad.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository):ViewModel() {
    private val _authState=MutableLiveData<Result<FirebaseUser?>>()
    val authState:LiveData<Result<FirebaseUser?>> get() = _authState

    fun signInWithGoogle(idToken:String){
        viewModelScope.launch(Dispatchers.IO) {
            val result=repository.signInWithGoogle(idToken)
            _authState.value=result
        }
    }
    fun getCurrentUser():FirebaseUser?=repository.getCurrentUser()
    fun signOut()=repository.signOut()
}