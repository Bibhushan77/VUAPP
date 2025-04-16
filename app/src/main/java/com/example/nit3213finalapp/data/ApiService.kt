package com.example.nit3213finalapp.data

import com.example.nit3213finalapp.data.models.DashboardResponse
import com.example.nit3213finalapp.data.models.LoginRequest
import com.example.nit3213finalapp.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface  ApiService {
    @POST("{location}/auth")
    suspend fun login(
        @Path("location") location: String,
        @Body request: LoginRequest
    ): Response<LoginResponse>


    @GET("dashboard/{keypass}")
    suspend fun getDashboardData(
        @Path("keypass") keypass: String
    ): Response<DashboardResponse>

}