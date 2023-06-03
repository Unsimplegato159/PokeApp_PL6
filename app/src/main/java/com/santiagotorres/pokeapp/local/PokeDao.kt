package com.santiagotorres.pokeapp.local

import androidx.room.*
import com.santiagotorres.pokeapp.local.model.LocalPoke

@Dao
interface PokeDao {
    @Insert
    suspend fun  savePoke(poke: LocalPoke)

    @Delete
    suspend fun deletePoke(poke: LocalPoke)

    @Query("SELECT * FROM table_pokes WHERE id Like :id")
    suspend fun searchPoke(id: Int): LocalPoke

    @Query("SELECT * FROM table_pokes")
    suspend fun getAllFavoritePokemon(): List<LocalPoke>
    }
