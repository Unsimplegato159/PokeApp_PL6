package com.santiagotorres.pokeapp.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.santiagotorres.pokeapp.R
import com.santiagotorres.pokeapp.databinding.CardPokemonSearchBinding
import com.santiagotorres.pokeapp.local.model.LocalPoke
import com.santiagotorres.pokeapp.local.repository.LocalPokeRepository
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {
    private val localPokeRepository = LocalPokeRepository()

    val favoritePokemonList = MutableLiveData<List<LocalPoke>>()

    init {
        loadFavoritePokemonList()
    }

    private fun loadFavoritePokemonList() {
        viewModelScope.launch {
            val list = localPokeRepository.getAllFavoritePokemon()
            favoritePokemonList.postValue(list)
        }
    }
}