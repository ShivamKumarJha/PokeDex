package com.shivamkumarjha.pokedex.ui.pokemons

import androidx.lifecycle.*
import com.shivamkumarjha.pokedex.config.Constants
import com.shivamkumarjha.pokedex.model.PokemonMain
import com.shivamkumarjha.pokedex.network.Resource
import com.shivamkumarjha.pokedex.persistence.PokemonDao
import com.shivamkumarjha.pokedex.repository.PokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val pokeRepository: PokeRepository,
    private val pokemonDao: PokemonDao
) : ViewModel() {

    private val _pokemonMain = MutableLiveData<Resource<PokemonMain?>>()
    val pokemonMain: LiveData<Resource<PokemonMain?>> = _pokemonMain

    val pokemons = liveData(Dispatchers.IO) {
        emitSource(pokemonDao.getPokemons())
    }

    init {
        getPokemons()
    }

    fun getPokemons(offset: Int = Constants.DEFAULT_OFFSET) {
        viewModelScope.launch(Dispatchers.IO) {
            pokeRepository.getPokemons(offset).collect {
                _pokemonMain.postValue(it)
            }
        }
    }
}