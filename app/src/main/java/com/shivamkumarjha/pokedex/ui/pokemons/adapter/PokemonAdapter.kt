package com.shivamkumarjha.pokedex.ui.pokemons.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.card.MaterialCardView
import com.shivamkumarjha.pokedex.R
import com.shivamkumarjha.pokedex.databinding.ItemPokemonBinding
import com.shivamkumarjha.pokedex.model.PokemonData

class PokemonAdapter(private val clickListener: PokemonClickListener) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var pokemons: List<PokemonData> = arrayListOf()

    override fun getItemCount(): Int = pokemons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPokemons(pokemons: List<PokemonData>) {
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

        fun bind(pokemon: PokemonData) {
            card.setOnClickListener {
                clickListener.onCardClick(pokemon)
            }
            name.text = pokemon.name

            Glide.with(image.context)
                .asBitmap()
                .load(pokemon.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(circularProgressDrawable)

                .listener(object : RequestListener<Bitmap?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        circularProgressDrawable.stop()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (resource != null) {
                            val palette: Palette = Palette.from(resource).generate()
                            card.setCardBackgroundColor(
                                palette.getDominantColor(image.context.getColor(R.color.white))
                            )
                        }
                        return false
                    }
                })
                .into(image)
        }
    }
}