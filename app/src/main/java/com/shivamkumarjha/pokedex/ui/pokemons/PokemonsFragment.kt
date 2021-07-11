package com.shivamkumarjha.pokedex.ui.pokemons

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.pokedex.R
import com.shivamkumarjha.pokedex.databinding.FragmentPokemonsBinding
import com.shivamkumarjha.pokedex.model.PokemonData
import com.shivamkumarjha.pokedex.network.Status
import com.shivamkumarjha.pokedex.ui.extensions.toast
import com.shivamkumarjha.pokedex.ui.pokemons.adapter.PokemonAdapter
import com.shivamkumarjha.pokedex.ui.pokemons.adapter.PokemonClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonsFragment : Fragment(R.layout.fragment_pokemons) {
    //Views
    private var binding: FragmentPokemonsBinding? = null
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter

    //ViewModel
    private val viewModel: PokemonsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)

        // SearchView
        val searchItem: MenuItem = menu.findItem(R.id.list_search_id)
        val searchView: SearchView = searchItem.actionView as SearchView
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

    override fun onViewCreated(view: View, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        binding = FragmentPokemonsBinding.bind(view)
        setViews()
        observer()
    }

    private fun setViews() {
        toolbar = binding!!.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

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
        val mLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = pokemonAdapter
            layoutManager = mLayoutManager
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
                binding?.fabScroll?.isVisible = mLayoutManager.findFirstVisibleItemPosition() > 0
            }
        })
        binding?.fabScroll?.setOnClickListener {
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

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}