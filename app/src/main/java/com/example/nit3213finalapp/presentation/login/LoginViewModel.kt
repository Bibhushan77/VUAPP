package com.example.nit3213finalapp.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nit3213finalapp.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    // livedata to check if login is successful
    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> = _loginState

    // for showing error msgs if login fails
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    // just return whatever keypass saved in repo
    fun getKeyPass(): String {
        return repository.getKeyPass()
    }

    // login logic goes here
    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)

                // if login success
                if (response.isSuccessful) {
                    Log.d("LOGIN_SUCCESS", "Response: ${response.body()}")

                    val keypass = response.body()?.keypass

                    Log.e("",keypass.toString());

                    // saving keypass in repo
                    repository.saveKeyPass(keypass!!)

                    // update success
                    _loginState.value = true
                } else {
                    // wrong creds probably
                    Log.e("LOGIN_ERROR", "Error Response: ${response.toString()}")
                    _errorMessage.value = "Login either pwd or username wrong!"
                }
            } catch (e: Exception) {
                // something bad happened like network or crash
                _errorMessage.value = "An error occurred!"
                Log.e("error",e.toString());
                _errorMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}
