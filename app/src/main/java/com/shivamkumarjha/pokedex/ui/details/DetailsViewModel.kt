package com.shivamkumarjha.pokedex.ui.details

import androidx.lifecycle.*
import com.shivamkumarjha.pokedex.model.PokemonDetails
import com.shivamkumarjha.pokedex.network.Resource
import com.shivamkumarjha.pokedex.persistence.PokemonDao
import com.shivamkumarjha.pokedex.repository.PokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val pokeRepository: PokeRepository,
    private val pokemonDao: PokemonDao
) : ViewModel() {

    private val _pokemonDetails = MutableLiveData<Resource<PokemonDetails?>>()
    val pokemonDetails: LiveData<Resource<PokemonDetails?>> = _pokemonDetails

    fun getPokemonDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            pokeRepository.getPokemonDetails(id).collect {
                _pokemonDetails.postValue(it)
            }
        }
    }

    fun pokemons(id: Int) = liveData(Dispatchers.IO) {
        emitSource(pokemonDao.getPokemonDetail(id))
    }
}