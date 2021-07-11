package com.shivamkumarjha.pokedex.ui.details.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.shivamkumarjha.pokedex.databinding.ItemTypesBinding
import com.shivamkumarjha.pokedex.model.Types
import com.shivamkumarjha.pokedex.ui.extensions.getColorById
import com.shivamkumarjha.pokedex.utility.PokemonUtility

class TypeAdapter : RecyclerView.Adapter<TypeAdapter.TypeViewHolder>() {

    private var types: List<Types> = arrayListOf()

    override fun getItemCount(): Int = types.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeAdapter.TypeViewHolder {
        val binding = ItemTypesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TypeAdapter.TypeViewHolder, position: Int) {
        holder.bind(types[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTypes(types: List<Types>) {
        this.types = types
        notifyDataSetChanged()
    }

    inner class TypeViewHolder(binding: ItemTypesBinding) : RecyclerView.ViewHolder(binding.root) {
        private val card: MaterialCardView = binding.cardView
        private val name: AppCompatTextView = binding.name

        fun bind(type: Types) {
            card.setCardBackgroundColor(card.context.getColorById(PokemonUtility.getTypeColor(type.type.name)))
            name.text = type.type.name
        }
    }
}