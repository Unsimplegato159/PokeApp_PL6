package com.santiagotorres.pokeapp.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiagotorres.pokeapp.local.model.LocalPoke
import com.santiagotorres.pokeapp.local.repository.LocalPokeRepository
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    private val localPokeRepository = LocalPokeRepository()
    val favoritePokemonList = MutableLiveData<List<LocalPoke>>()

    init {
        loadFavoritePokemonList()
    }

    fun loadFavoritePokemonList() {
        viewModelScope.launch {
            //localPokeRepository.getAllFavoritePokemon()
            val list = localPokeRepository.getAllFavoritePokemon()
            favoritePokemonList.postValue(list)
        }
    }



}