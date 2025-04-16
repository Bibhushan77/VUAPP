package com.example.nit3213finalapp.data

import android.util.Log
import com.example.nit3213finalapp.data.models.LoginRequest
import com.example.nit3213finalapp.data.models.LoginResponse
import retrofit2.Response
import javax.inject.Inject

// interface for auth related stuff
interface AuthRepository {
    suspend fun login(username: String, password: String): Response<LoginResponse>
    fun getKeyPass(): String
    fun saveKeyPass(keypass: String)
}

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {

    private var keypass: String? = null

    // login call using username & pwd
    override suspend fun login(username: String, password: String): Response<LoginResponse> {
        val location = "sydney" // hardcoded location lol
        val request = LoginRequest(username, password)
        return apiService.login(location, request)
    }

    // saving keypass to use later
    override fun saveKeyPass(keypass: String) {
        Log.e("Saving keypass", keypass)
        this.keypass = keypass
    }

    // returning the saved keypass
    override fun getKeyPass(): String {
        Log.e("Getting keypass", keypass.toString())
        return keypass ?: "" // return empty if null
    }
}
