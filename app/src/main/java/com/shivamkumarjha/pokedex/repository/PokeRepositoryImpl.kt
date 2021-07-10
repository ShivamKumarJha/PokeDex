package com.shivamkumarjha.pokedex.repository

import android.util.Log
import com.shivamkumarjha.pokedex.config.Constants
import com.shivamkumarjha.pokedex.model.PokemonDetails
import com.shivamkumarjha.pokedex.model.PokemonMain
import com.shivamkumarjha.pokedex.network.ApiPoke
import com.shivamkumarjha.pokedex.network.NoConnectivityException
import com.shivamkumarjha.pokedex.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PokeRepositoryImpl(private val apiPoke: ApiPoke) : PokeRepository {

    override suspend fun getPokemons(offset: Int): Flow<Resource<PokemonMain?>> = flow {
        emit(Resource.loading(data = null))
        try {
            val response = apiPoke.getPokemons(offset)
            if (response.isSuccessful) {
                emit(Resource.success(data = response.body()))
                Log.d(Constants.TAG, response.body().toString())
            } else {
                emit(Resource.error(data = null, message = response.code().toString()))
                Log.d(Constants.TAG, response.code().toString())
            }
        } catch (exception: Exception) {
            if (exception is NoConnectivityException)
                emit(Resource.offline(data = null))
            else {
                emit(Resource.error(data = null, message = exception.message.toString()))
                Log.e(Constants.TAG, exception.message.toString())
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPokemonDetails(id: Int): Flow<Resource<PokemonDetails?>> = flow {
        emit(Resource.loading(data = null))
        try {
            val response = apiPoke.getPokemonDetails(id)
            if (response.isSuccessful) {
                emit(Resource.success(data = response.body()))
                Log.d(Constants.TAG, response.body().toString())
            } else {
                emit(Resource.error(data = null, message = response.code().toString()))
                Log.d(Constants.TAG, response.code().toString())
            }
        } catch (exception: Exception) {
            if (exception is NoConnectivityException)
                emit(Resource.offline(data = null))
            else {
                emit(Resource.error(data = null, message = exception.message.toString()))
                Log.e(Constants.TAG, exception.message.toString())
            }
        }
    }.flowOn(Dispatchers.IO)
}