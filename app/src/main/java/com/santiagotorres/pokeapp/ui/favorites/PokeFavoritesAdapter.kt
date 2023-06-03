package com.santiagotorres.pokeapp.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santiagotorres.pokeapp.R
import com.santiagotorres.pokeapp.databinding.CardPokemonSearchBinding
import com.santiagotorres.pokeapp.local.model.LocalPoke

class FavoritesListAdapter(private val pokemonClick: (Int) -> Unit) : RecyclerView.Adapter<FavoritesListAdapter.FavoriteViewHolder>() {
    private var favoritePokemonList: List<LocalPoke> = emptyList()

    fun setData(list: List<LocalPoke>) {
        favoritePokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon_search, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return favoritePokemonList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val localPoke = favoritePokemonList[position]
        holder.bindPokemon(localPoke)

        holder.itemView.setOnClickListener { localPoke.id?.let { it1 -> pokemonClick(it1) } }
    }

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardPokemonSearchBinding.bind(itemView)

        fun bindPokemon(localPoke: LocalPoke) {
            with(binding) {
                pokemonText.text = "#${localPoke.id} - ${localPoke.name}"
            }
        }
    }
}