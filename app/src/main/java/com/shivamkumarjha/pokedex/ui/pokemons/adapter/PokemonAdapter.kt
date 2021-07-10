package com.shivamkumarjha.pokedex.ui.pokemons.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import com.shivamkumarjha.pokedex.databinding.ItemPokemonBinding
import com.shivamkumarjha.pokedex.model.Result

class PokemonAdapter(private val clickListener: PokemonClickListener) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var pokemons: List<Result> = arrayListOf()

    override fun getItemCount(): Int = pokemons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPokemons(pokemons: List<Result>) {
        this.pokemons = pokemons
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder(binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val card: MaterialCardView = binding.cardView
        private val name: AppCompatTextView = binding.name
        private val image: AppCompatImageView = binding.image
        private var circularProgressDrawable: CircularProgressDrawable =
            CircularProgressDrawable(image.context)

        init {
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
        }

        fun bind(pokemon: Result) {
            card.setOnClickListener {
                clickListener.onCardClick(pokemon)
            }
            name.text = pokemon.name
            Glide.with(image.context)
                .load(pokemon.url)
                .placeholder(circularProgressDrawable)
                .apply(RequestOptions.centerCropTransform())
                .into(image)
        }
    }
}