package com.shivamkumarjha.pokedex.di

import com.google.gson.Gson
import com.shivamkumarjha.pokedex.config.Constants
import com.shivamkumarjha.pokedex.network.ApiPoke
import com.shivamkumarjha.pokedex.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun getApiPoke(okHttpClient: OkHttpClient, gson: Gson): ApiPoke =
        RetrofitClient.getClient(Constants.API_POKE, okHttpClient, gson)
            .create(ApiPoke::class.java)
}