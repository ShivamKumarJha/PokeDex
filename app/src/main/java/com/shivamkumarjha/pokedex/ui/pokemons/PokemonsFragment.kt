package com.shivamkumarjha.pokedex.ui.pokemons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.shivamkumarjha.pokedex.R
import com.shivamkumarjha.pokedex.databinding.FragmentPokemonsBinding

class PokemonsFragment : Fragment(R.layout.fragment_pokemons) {

    private var binding: FragmentPokemonsBinding? = null

    override fun onViewCreated(view: View, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        binding = FragmentPokemonsBinding.bind(view)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}