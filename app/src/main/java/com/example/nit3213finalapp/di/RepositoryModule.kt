package com.example.nit3213finalapp.di

import com.example.nit3213finalapp.data.AuthRepository
import com.example.nit3213finalapp.data.AuthRepositoryImpl
import com.example.nit3213finalapp.data.DashboardRepository
import com.example.nit3213finalapp.data.DashboardRepositoryImpl
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // binding Auth repo with its impl
    @Binds
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    // binding Dashboard repo with its impl
    @Binds
    abstract fun bindDashboardRepository(
        impl: DashboardRepositoryImpl
    ): DashboardRepository
}
