package com.example.nit3213finalapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
data class DashboardResponse(
    val entities: List<EntityItem>,
    val entityTotal: Int
)

@Parcelize
data class EntityItem(
    val itemName: String,
    val designer: String,
    val yearIntroduced: Int,
    val category: String,
    val material: String,
    val description: String
) : Parcelable
