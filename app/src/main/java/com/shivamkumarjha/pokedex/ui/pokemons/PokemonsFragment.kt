package com.shivamkumarjha.pokedex.ui.pokemons

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.pokedex.R
import com.shivamkumarjha.pokedex.databinding.FragmentPokemonsBinding
import com.shivamkumarjha.pokedex.model.PokemonData
import com.shivamkumarjha.pokedex.network.Status
import com.shivamkumarjha.pokedex.ui.extensions.getColorById
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
        searchPokemons()
        observer()
    }

    private fun setViews() {
        binding?.swipeRefresh?.setOnRefreshListener {
            binding?.swipeRefresh?.isRefreshing = false
            viewModel.clearPokemons()
            viewModel.getPokemons()
        }
        //Recycler view
        pokemonAdapter = PokemonAdapter(object : PokemonClickListener {
            override fun onCardClick(pokemonData: PokemonData) {
                val action =
                    PokemonsFragmentDirections.actionPokemonsFragmentToDetailsFragment(pokemonData.id)
                findNavController().navigate(action)
            }
        })
        recyclerView = binding!!.recyclerView
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = pokemonAdapter
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getPokemons(pokemonAdapter.itemCount)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding?.scrollTopCard?.isVisible = dy > 0
            }
        })
        binding?.scrollTopCard?.setOnClickListener {
            binding?.scrollTopCard?.isVisible = false
            recyclerView.smoothScrollToPosition(0)
        }
    }

    private fun observer() {
        viewModel.pokemonMain.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {

                    }
                    Status.ERROR -> {
                        requireContext().toast(getString(R.string.error_occurred, it.message))
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
        viewModel.pokemons.observe(viewLifecycleOwner, {
            it?.let { pokemons ->
                pokemonAdapter.setPokemons(pokemons)
            }
        })
    }

    private fun searchPokemons() {
        val searchView = binding!!.searchView
        val searchIcon = searchView.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)
        val cancelIcon = searchView.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)
        val searchTextView = searchView.findViewById<TextView>(R.id.search_src_text)
        searchTextView.setTextColor(Color.WHITE)
        searchTextView.hint = resources.getString(R.string.search_pokemons)
        searchTextView.setHintTextColor(requireContext().getColorById(R.color.grey_200))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                pokemonAdapter.filter.filter(newText)
                return false
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}