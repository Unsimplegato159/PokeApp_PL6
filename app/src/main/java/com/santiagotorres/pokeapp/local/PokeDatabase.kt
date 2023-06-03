package com.santiagotorres.pokeapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.santiagotorres.pokeapp.local.PokeDao
import com.santiagotorres.pokeapp.local.model.LocalPoke

@Database(entities =[LocalPoke :: class ], version=1)
abstract class PokeDatabase : RoomDatabase(){

    abstract fun PokeDao() : PokeDao
}