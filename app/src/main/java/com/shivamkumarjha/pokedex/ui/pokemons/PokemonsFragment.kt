package com.shivamkumarjha.pokedex.ui.pokemons

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.pokedex.R
import com.shivamkumarjha.pokedex.databinding.FragmentPokemonsBinding
import com.shivamkumarjha.pokedex.model.Result
import com.shivamkumarjha.pokedex.network.Status
import com.shivamkumarjha.pokedex.ui.extensions.toast
import com.shivamkumarjha.pokedex.ui.pokemons.adapter.PokemonAdapter
import com.shivamkumarjha.pokedex.ui.pokemons.adapter.PokemonClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonsFragment : Fragment(R.layout.fragment_pokemons) {
    //Views
    private var binding: FragmentPokemonsBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter

    //ViewModel
    private val viewModel: PokemonsViewModel by viewModels()

    override fun onViewCreated(view: View, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        binding = FragmentPokemonsBinding.bind(view)
        setViews()
        observer()
    }

    private fun setViews() {
        //Recycler view
        pokemonAdapter = PokemonAdapter(object : PokemonClickListener {
            override fun onCardClick(result: Result) {
                requireContext().toast(result.name)
            }
        })
        recyclerView = binding!!.recyclerView
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = pokemonAdapter
        }
    }

    private fun observer() {
        viewModel.pokemons.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {
                        pokemonAdapter.setPokemons(it.data?.results ?: arrayListOf())
                    }
                    Status.ERROR -> {
                        requireContext().toast(it.message ?: getString(R.string.error_occurred))
                    }
                    Status.LOADING -> {

                    }
                    Status.OFFLINE -> {
                        requireContext().toast(getString(R.string.user_offline))
                    }
                }
                binding?.progressbar?.isVisible = it.status == Status.LOADING
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}