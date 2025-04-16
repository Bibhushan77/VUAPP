package com.example.nit3213finalapp.presentation.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nit3213finalapp.data.DashboardRepository
import com.example.nit3213finalapp.data.models.EntityItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {

    // livedata for storing list of data
    private val _entities = MutableLiveData<List<EntityItem>>()
    val entities: LiveData<List<EntityItem>> = _entities

    // function for api call
    fun getDashboardData(keypass: String) {
        viewModelScope.launch {
            try {
                // calling repo function for api data
                val response = repository.getDashboardData(keypass)

                // if api hit success
                if (response.isSuccessful) {
                    Log.e("Dashboarddata", response.toString())
                    Log.e("Ent", response.body()?.entities.toString())

                    // updating livedata with data
                    _entities.value = response.body()?.entities ?: emptyList()
                }
            } catch (e: Exception) {
                // error in api call or something went wrong
                Log.e("Error", e.toString())
            }
        }
    }
}
