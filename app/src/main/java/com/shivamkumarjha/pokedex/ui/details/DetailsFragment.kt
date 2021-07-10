package com.shivamkumarjha.pokedex.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shivamkumarjha.pokedex.R
import com.shivamkumarjha.pokedex.databinding.FragmentDetailsBinding
import com.shivamkumarjha.pokedex.network.Status
import com.shivamkumarjha.pokedex.ui.extensions.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    //Views
    private var binding: FragmentDetailsBinding? = null

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
            }
        })
        viewModel.pokemons(args.id).observe(viewLifecycleOwner, {
            it?.let { pokemonDetails ->
                binding?.name?.text = pokemonDetails.name
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}