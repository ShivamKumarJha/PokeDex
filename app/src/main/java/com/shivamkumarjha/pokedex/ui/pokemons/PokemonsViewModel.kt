package com.shivamkumarjha.pokedex.ui.pokemons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivamkumarjha.pokedex.config.Constants
import com.shivamkumarjha.pokedex.model.PokemonMain
import com.shivamkumarjha.pokedex.network.Resource
import com.shivamkumarjha.pokedex.repository.PokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : ViewModel() {

    private val _pokemons = MutableLiveData<Resource<PokemonMain?>>()
    val pokemons: LiveData<Resource<PokemonMain?>> = _pokemons

    init {
        getPokemons()
    }

    fun getPokemons(offset: Int = Constants.DEFAULT_OFFSET) {
        viewModelScope.launch(Dispatchers.IO) {
            pokeRepository.getPokemons(offset).collect {
                _pokemons.postValue(it)
            }
        }
    }
}