package com.example.firebaseapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel(private val authRepository: FirebaseAuthRepository) : ViewModel() {

    private val _authState = MutableLiveData<Boolean>()
    val authState: LiveData<Boolean> get() = _authState

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun register(email: String, password: String) {
        authRepository.registerUser(email, password) { success, message ->
            if (success) {
                _authState.value = true
            } else {
                _authState.value = false
                _errorMessage.value = message
            }
        }
    }

    fun login(email: String, password: String) {
        authRepository.loginUser(email, password) { success, message ->
            if (success) {
                _authState.value = true
            } else {
                _authState.value = false
                _errorMessage.value = message
            }
        }
    }
}
