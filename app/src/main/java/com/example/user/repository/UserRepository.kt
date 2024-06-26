package com.example.user.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.user.api.UserService
import com.example.user.model.Users

class UserRepository(private val service:UserService) {
    private val userLiveData = MutableLiveData<Users>()
    val user:LiveData<Users>
        get() = userLiveData

    suspend fun getUsers(){
        val result = service.getUser()
        if (result.isSuccessful){
            if (result.body() != null){
                userLiveData.postValue(result.body())
            }
        }
      else{
            Log.e("ERROR",result.code().toString())
        }
    }
}