package com.example.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.user.model.Users
import com.example.user.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository):ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        _isLoading.postValue(true)
         viewModelScope.launch(Dispatchers.IO)
         {
            try {
                repository.getUsers()
            }catch (e: Exception) {
                _error.postValue(e.message)
            }
            finally {
                _isLoading.postValue(false)
            }
         }
    }
    val user:LiveData<Users>
        get() = repository.user

}