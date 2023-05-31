package com.santiagotorres.pokeapp.ui.list

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiagotorres.pokeapp.R
import com.santiagotorres.pokeapp.ui.pokeinfo.PokeInfoActivity
import com.santiagotorres.pokeapp.databinding.ActivityPokemonlistBinding


class PokemonListActivity : AppCompatActivity() {

    private lateinit var pokemonlistBinding: ActivityPokemonlistBinding
    private lateinit var viewModel: PokemonListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonlistBinding = ActivityPokemonlistBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[PokemonListViewModel::class.java]


        val view = pokemonlistBinding.root
        setContentView(view)

        pokemonlistBinding.buttonUp.setOnClickListener {
            pokemonlistBinding.pokemonlistRecyclerView.smoothScrollToPosition(0)
        }

        initUI()

    }

    private fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    private fun initUI(){
        pokemonlistBinding.pokemonlistRecyclerView.layoutManager = LinearLayoutManager(this)
        pokemonlistBinding.pokemonlistRecyclerView.adapter = PokemonListAdapter{
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        viewModel.getPokemonList()

        viewModel.pokemonList.observe(this, Observer { list ->
            (pokemonlistBinding.pokemonlistRecyclerView.adapter as PokemonListAdapter).setData(list)
        })
    }
}