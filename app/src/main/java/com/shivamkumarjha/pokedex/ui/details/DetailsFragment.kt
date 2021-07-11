package com.shivamkumarjha.pokedex.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.pokedex.R
import com.shivamkumarjha.pokedex.databinding.FragmentDetailsBinding
import com.shivamkumarjha.pokedex.network.Status
import com.shivamkumarjha.pokedex.ui.details.adapter.SlidesAdapter
import com.shivamkumarjha.pokedex.ui.details.adapter.TypeAdapter
import com.shivamkumarjha.pokedex.ui.extensions.toast
import com.shivamkumarjha.pokedex.utility.PokemonUtility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    //Views
    private var binding: FragmentDetailsBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var typeAdapter: TypeAdapter

    //Images ViewPager
    private lateinit var slidesAdapter: SlidesAdapter

    //Navigation arguments
    private val args: DetailsFragmentArgs by navArgs()

    //ViewModel
    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        binding = FragmentDetailsBinding.bind(view)
        setViews()
        observer()
    }

    private fun setViews() {
        binding?.arrow?.setOnClickListener {
            findNavController().navigateUp()
        }
        //Recycler view
        recyclerView = binding!!.typesRecyclerView
        typeAdapter = TypeAdapter()
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = typeAdapter
        }
    }

    private fun observer() {
        viewModel.getPokemonDetails(args.id)
        viewModel.pokemonDetails.observe(viewLifecycleOwner, {
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
        viewModel.pokemons(args.id).observe(viewLifecycleOwner, {
            it?.let { pokemonDetails ->
                binding?.name?.text = pokemonDetails.name
                binding?.index?.text = PokemonUtility.styleId(pokemonDetails.id)
                binding?.height?.text = getString(
                    R.string.height_value,
                    (pokemonDetails.height.toFloat() / 10).toString()
                )
                binding?.weight?.text = getString(
                    R.string.weight_value,
                    (pokemonDetails.weight.toFloat() / 10).toString()
                )

                slidesAdapter = SlidesAdapter(PokemonUtility.populateImages(pokemonDetails))
                binding?.slidesViewPager?.adapter = slidesAdapter

                typeAdapter.setTypes(pokemonDetails.types)
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}