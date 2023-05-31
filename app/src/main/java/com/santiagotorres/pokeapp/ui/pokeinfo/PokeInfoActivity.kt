package com.santiagotorres.pokeapp.ui.pokeinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.santiagotorres.pokeapp.databinding.ActivityPokeinfoBinding

class PokeInfoActivity : AppCompatActivity() {

    private lateinit var pokeinfoBinding: ActivityPokeinfoBinding
    lateinit var viewModel: PokeInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokeinfoBinding = ActivityPokeinfoBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[PokeInfoViewModel::class.java]

        val view = pokeinfoBinding.root
        setContentView(view)


        initUI()
    }

    private fun initUI(){
        val id = intent.extras?.get("id") as Int

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            pokeinfoBinding.nameTextView.text = pokemon.name
            pokeinfoBinding.heightText.text = "Altura: ${pokemon.height/10.0}m"
            pokeinfoBinding.weightText.text = "Peso: ${pokemon.weight/10.0}Kg"

            Glide.with(this).load(pokemon.sprites.frontDefault).into(pokeinfoBinding.imageView)
        })
    }
}