package com.santiagotorres.pokeapp.ui.pokeinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiagotorres.pokeapp.local.model.LocalPoke
import com.santiagotorres.pokeapp.local.repository.LocalPokeRepository
import com.santiagotorres.pokeapp.server.ApiService
import com.santiagotorres.pokeapp.server.model.Pokemon
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeInfoViewModel() : ViewModel() {

    private val localPokeRepository = LocalPokeRepository()
    private val _isPokeRemovedFromFavorites: MutableLiveData<Boolean> = MutableLiveData()
    val isPokeRemovedFromFavorites: LiveData<Boolean> = _isPokeRemovedFromFavorites

    private val _isPokeFavorite : MutableLiveData <Boolean> = MutableLiveData()
    val isPokeFavorite : LiveData<Boolean> = _isPokeFavorite


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ApiService = retrofit.create(ApiService::class.java)

    val pokemonInfo = MutableLiveData<Pokemon>()

    fun getPokemonInfo(id: Int) {
        val call = service.getPokemonInfo(id)

        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }

        })
    }

    //companion object {
    fun savePoke(pokemon: Pokemon) {
        val localPoke= LocalPoke(
            id= pokemon.id,
            name= pokemon.name,
            height= pokemon.height,
            weight= pokemon.weight


        )

        viewModelScope.launch{
            localPokeRepository.savePoke(localPoke)
        }
    }

    fun searchPoke(id: Int) {
        var pokeFavorite = false
        viewModelScope.launch{
            val localPoke= localPokeRepository.searchPoke(id)
            if(localPoke != null)
                pokeFavorite=true
            _isPokeFavorite.postValue(pokeFavorite)


        }
    }

    fun deletePoke(pokemon: Pokemon) {
        viewModelScope.launch {
            val localPoke = localPokeRepository.searchPoke(pokemon.id)
            localPoke?.let {
                localPokeRepository.deletePoke(localPoke)
                _isPokeRemovedFromFavorites.postValue(true)
            }
        }
    }


}
