package com.santiagotorres.pokeapp

import android.app.Application
import androidx.room.Room
import com.santiagotorres.pokeapp.local.PokeDatabase

class Pokeapp : Application() {

    companion object {
        lateinit var database : PokeDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            PokeDatabase::class.java,
            "poke_db"
        ).build()
    }
}