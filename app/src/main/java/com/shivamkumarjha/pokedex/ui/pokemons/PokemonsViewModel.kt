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

    fun pokemons(filter: Int) = liveData(Dispatchers.IO) {
        when (filter) {
            0 -> emitSource(pokemonDao.getPokemonsIdAsc())
            1 -> emitSource(pokemonDao.getPokemonsIdDesc())
            2 -> emitSource(pokemonDao.getPokemonsNameAsc())
            3 -> emitSource(pokemonDao.getPokemonsNameDesc())
        }
    }

    //Stores offsets called for paging
    private val offsetList: ArrayList<Int> = arrayListOf()

    init {
        getPokemons()
    }

    fun clearPokemons() {
        offsetList.clear()
        viewModelScope.launch(Dispatchers.IO) {
            pokemonDao.clearPokemons()
        }
    }

    fun getPokemons(offset: Int = Constants.DEFAULT_OFFSET) {
        if (offsetList.contains(offset))
            return
        offsetList.add(offset)
        viewModelScope.launch(Dispatchers.IO) {
            pokeRepository.getPokemons(offset).collect {
                _pokemonMain.postValue(it)
            }
        }
    }
}