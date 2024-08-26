package com.example.note_chad.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val auth:FirebaseAuth) {

    suspend fun signInWithGoogle(idToken:String):Result<FirebaseUser?>
    {
        return try{
            val credential=GoogleAuthProvider.getCredential(idToken,null)
            val authResult=auth.signInWithCredential(credential).await()
            Result.success(authResult.user)
        }
        catch (e:Exception){
            Result.failure(e)
        }
    }
    fun getCurrentUser()=auth.currentUser
    fun signOut()=auth.signOut()
}