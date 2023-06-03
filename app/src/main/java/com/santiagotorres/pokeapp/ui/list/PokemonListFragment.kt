package com.santiagotorres.pokeapp.ui.list

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.santiagotorres.pokeapp.R
import com.santiagotorres.pokeapp.databinding.CardPokemonSearchBinding
import com.santiagotorres.pokeapp.databinding.FragmentPokemonListBinding
import com.santiagotorres.pokeapp.local.model.LocalPoke
import com.santiagotorres.pokeapp.ui.favorites.FavoritesFragment
import com.santiagotorres.pokeapp.ui.pokeinfo.PokeInfoActivity

class PokemonListFragment : Fragment() {

    private lateinit var pokemonlistBinding: FragmentPokemonListBinding
    private lateinit var viewModel: PokemonList_ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pokemonlistBinding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return pokemonlistBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[PokemonList_ViewModel::class.java]
        val buttonUp = view.findViewById<Button>(R.id.button_up)
        buttonUp.setOnClickListener {
            val intent = Intent(requireContext(), FavoritesFragment::class.java)
            startActivity(intent)
        }

        initUI()
    }

    private fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    private fun initUI() {
        pokemonlistBinding.pokemonlistRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        pokemonlistBinding.pokemonlistRecyclerView.adapter = PokemonListAdapter{
            val intent = Intent(requireContext(), PokeInfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        viewModel.getPokemonList()

        viewModel.pokemonList.observe(viewLifecycleOwner, Observer { list ->
            (pokemonlistBinding.pokemonlistRecyclerView.adapter as PokemonListAdapter).setData(list)
        })



    }
}