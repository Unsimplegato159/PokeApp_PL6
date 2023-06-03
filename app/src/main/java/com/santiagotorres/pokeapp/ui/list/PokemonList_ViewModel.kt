package com.santiagotorres.pokeapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santiagotorres.pokeapp.server.ApiService
import com.santiagotorres.pokeapp.server.model.PokeApiResponse
import com.santiagotorres.pokeapp.server.model.PokeResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonList_ViewModel() : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ApiService = retrofit.create(ApiService::class.java)

    private val _favoritePokemonUpdated = MutableLiveData<Unit>()
    val favoritePokemonUpdated: LiveData<Unit> = _favoritePokemonUpdated

    fun updateFavoritePokemonList() {
        _favoritePokemonUpdated.value = Unit
    }

    val pokemonList = MutableLiveData<List<PokeResult>>()

    fun getPokemonList(){
        val call = service.getPokemonList(200,0)

        call.enqueue(object : Callback<PokeApiResponse> {
            override fun onResponse(call: Call<PokeApiResponse>, response: Response<PokeApiResponse>) {
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list)
                }

            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                call.cancel()
            }

        }
        )
    }
}