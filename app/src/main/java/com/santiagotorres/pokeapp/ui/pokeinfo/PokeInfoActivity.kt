package com.santiagotorres.pokeapp.ui.pokeinfo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.santiagotorres.pokeapp.R
import com.santiagotorres.pokeapp.databinding.ActivityPokeinfoBinding

class PokeInfoActivity : AppCompatActivity() {

    private lateinit var pokeinfoBinding: ActivityPokeinfoBinding
    private lateinit var viewModel: PokeInfoViewModel
    private var isPokeFavorite = false
    private var isFavoriteChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokeinfoBinding = ActivityPokeinfoBinding.inflate(layoutInflater)
        setContentView(pokeinfoBinding.root)


        viewModel = ViewModelProvider(this)[PokeInfoViewModel::class.java]

        initUI()

        viewModel.isPokeFavorite.observe(this, Observer { isPokeFavorite ->
            this.isPokeFavorite = isPokeFavorite

            if (isPokeFavorite)
                pokeinfoBinding.favoritesImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite
                    )
                )

            else
                pokeinfoBinding.favoritesImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite_border
                    )
                )
        })

        viewModel.isPokeRemovedFromFavorites.observe(this, Observer { isPokeRemoved ->
            if (isPokeRemoved) {
                Toast.makeText(this, "Eliminado de favoritos", Toast.LENGTH_SHORT).show()
            }
           // FavoritesFragment().viewModel.loadFavoritePokemonList()
        })
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("favoriteChanged", isFavoriteChanged)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
        //supportFragmentManager.beginTransaction().replace(R.id.container, FavoritesFragment()).commit()

    }

    private fun initUI() {
        val id = intent.extras?.getInt("id") ?: -1

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this) { pokemon ->
            pokeinfoBinding.nameTextView.text = pokemon.name
            pokeinfoBinding.heightText.text = "Altura: ${pokemon.height / 10.0}m"
            pokeinfoBinding.weightText.text = "Peso: ${pokemon.weight / 10.0}Kg"

            viewModel.searchPoke(pokemon.id)

            supportActionBar?.title = "${pokemon.name}"


            Glide.with(this).load(pokemon.sprites.frontDefault).into(pokeinfoBinding.imageView)


            pokeinfoBinding.favoritesImageView.setOnClickListener {

                if (isPokeFavorite) {
                    isPokeFavorite = false
                    pokeinfoBinding.favoritesImageView.setImageResource(R.drawable.ic_favorite_border)
                    viewModel.deletePoke(pokemon)
                    isFavoriteChanged = true


                } else {
                    isPokeFavorite = true
                    pokeinfoBinding.favoritesImageView.setImageResource(R.drawable.ic_favorite)
                    viewModel.savePoke(pokemon)
                    isFavoriteChanged = true
                    Toast.makeText(this, "Agregado a favoritos", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    companion object {
        private const val FAVORITE_CHANGE_REQUEST_CODE = 1001
    }
}