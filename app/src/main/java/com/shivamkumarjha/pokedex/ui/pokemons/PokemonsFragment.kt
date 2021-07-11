package com.shivamkumarjha.pokedex.ui.pokemons

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
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
import com.shivamkumarjha.pokedex.databinding.DialogSortBinding
import com.shivamkumarjha.pokedex.databinding.FragmentPokemonsBinding
import com.shivamkumarjha.pokedex.model.PokemonData
import com.shivamkumarjha.pokedex.network.Status
import com.shivamkumarjha.pokedex.persistence.PreferenceManager
import com.shivamkumarjha.pokedex.ui.extensions.toast
import com.shivamkumarjha.pokedex.ui.pokemons.adapter.PokemonAdapter
import com.shivamkumarjha.pokedex.ui.pokemons.adapter.PokemonClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonsFragment : Fragment(R.layout.fragment_pokemons) {
    //Views
    private var binding: FragmentPokemonsBinding? = null
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter

    //ViewModel
    private val viewModel: PokemonsViewModel by viewModels()

    @Inject
    lateinit var preferenceManager: PreferenceManager

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
        binding?.fabSort?.setOnClickListener {
            sortDialog()
        }
        //Recycler view
        pokemonAdapter = PokemonAdapter(object : PokemonClickListener {
            override fun onCardClick(pokemonData: PokemonData) {
                val action =
                    PokemonsFragmentDirections.actionPokemonsFragmentToDetailsFragment(pokemonData.id)
                findNavController().navigate(action)
            }
        })
        pokemonAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
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
        listObserver()
    }

    private fun listObserver(sort: Int = preferenceManager.sort) {
        viewModel.pokemons(sort).observe(viewLifecycleOwner, {
            it?.let { pokemons ->
                pokemonAdapter.setPokemons(pokemons)
            }
        })
    }

    private fun sortDialog() {
        val dialog = Dialog(requireContext())
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dialogBinding = DialogSortBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        when (preferenceManager.sort) {
            0 -> dialogBinding.radioGroup.check(R.id.radioIdAsc)
            1 -> dialogBinding.radioGroup.check(R.id.radioIdDesc)
            2 -> dialogBinding.radioGroup.check(R.id.radioNameAsc)
            3 -> dialogBinding.radioGroup.check(R.id.radioNameDesc)
        }
        dialogBinding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioIdAsc -> preferenceManager.sort = 0
                R.id.radioIdDesc -> preferenceManager.sort = 1
                R.id.radioNameAsc -> preferenceManager.sort = 2
                R.id.radioNameDesc -> preferenceManager.sort = 3
            }
            listObserver(preferenceManager.sort)
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}