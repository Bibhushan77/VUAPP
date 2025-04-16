package com.example.nit3213finalapp.data

import android.util.Log
import com.example.nit3213finalapp.data.models.DashboardResponse
import retrofit2.Response
import javax.inject.Inject

// interface for dashboard repo
interface DashboardRepository {
    suspend fun getDashboardData(keypass: String): Response<DashboardResponse>
}

// repo impl class
class DashboardRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val authRepository: AuthRepository
) : DashboardRepository {

    // getting data from api
    override suspend fun getDashboardData(keypass: String): Response<DashboardResponse> {
        Log.e("FROM REPO", keypass) // just for checking if data coming
        return apiService.getDashboardData(keypass) // calling api
    }
}
