package com.example.nit3213finalapp.di

import com.example.nit3213finalapp.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // providing retrofit instance
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://nit3213api.onrender.com/") // api base url
            .addConverterFactory(GsonConverterFactory.create()) // for json convert
            .build()

    // providing api service using retrofit
    @Provides
    fun provideApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}
