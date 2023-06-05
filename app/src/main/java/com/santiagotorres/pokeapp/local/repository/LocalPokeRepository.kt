package com.santiagotorres.pokeapp.local.repository

import com.santiagotorres.pokeapp.local.PokeDao
import com.santiagotorres.pokeapp.local.model.LocalPoke
import com.santiagotorres.pokeapp.Pokeapp

class LocalPokeRepository {
    private val pokeDao: PokeDao = Pokeapp.database.PokeDao()

    suspend fun savePoke(localPoke: LocalPoke) {
        pokeDao.savePoke(localPoke)
    }

    suspend fun searchPoke(id: Int): LocalPoke? {
        return pokeDao.searchPoke(id)
    }

    suspend fun getAllFavoritePokemon(): List<LocalPoke> {
        return pokeDao.getAllFavoritePokemon()
    }

    suspend fun deletePoke(localPoke: LocalPoke) {
        pokeDao.deletePoke(localPoke)
    }
}