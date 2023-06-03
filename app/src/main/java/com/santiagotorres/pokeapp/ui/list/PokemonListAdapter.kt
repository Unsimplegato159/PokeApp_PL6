package com.santiagotorres.pokeapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santiagotorres.pokeapp.R
import com.santiagotorres.pokeapp.databinding.CardPokemonSearchBinding
import com.santiagotorres.pokeapp.local.model.LocalPoke
import com.santiagotorres.pokeapp.server.model.PokeResult

class PokemonListAdapter(val pokemonClick: (Int) -> Unit): RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>() {
    private var pokemonList: List<PokeResult> = emptyList<PokeResult>()

    fun setData(list: List<PokeResult>){
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon_search, parent,false)
        return SearchViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bindPokemon(pokemon)
        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    //class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val binding = CardPokemonSearchBinding.bind(itemView)

        fun bindPokemon (pokemon: PokeResult) {
            with(binding){
                pokemonText.text = "#${position + 1} - ${pokemon.name}"
            }
        }
    }
}