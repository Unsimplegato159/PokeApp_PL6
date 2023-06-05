package com.santiagotorres.pokeapp.ui.favorites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiagotorres.pokeapp.R
import com.santiagotorres.pokeapp.databinding.FragmentFavoritesBinding
import com.santiagotorres.pokeapp.local.model.LocalPoke
import com.santiagotorres.pokeapp.ui.pokeinfo.PokeInfoActivity
import com.santiagotorres.pokeapp.local.repository.LocalPokeRepository
import kotlinx.coroutines.launch


class FavoritesFragment : Fragment() {
    private lateinit var favoritesBinding: FragmentFavoritesBinding
    lateinit var viewModel: FavoritesViewModel
    private lateinit var pokeFavoritesAdapter: PokeFavoritesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoritesBinding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return favoritesBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]

        val buttonRecharge = view.findViewById<Button>(R.id.button_recharge)

        buttonRecharge.setOnClickListener {
            viewModel.loadFavoritePokemonList()

        }

        initUI()


    }

    private fun initUI() {
///////////////////////////////////////////////////
        viewModel.loadFavoritePokemonList()
///////////////////////////////////////////////////

        pokeFavoritesAdapter = PokeFavoritesAdapter { id ->
            val intent = Intent(requireContext(), PokeInfoActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        favoritesBinding.pokemonFavoritesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FavoritesFragment.requireContext())
            adapter = pokeFavoritesAdapter

        }
        //viewModel.loadFavoritePokemonList()
        observeFavoritePokemonList()

    }

    private fun observeFavoritePokemonList() {
        viewModel.favoritePokemonList.observe(viewLifecycleOwner) { list ->
            pokeFavoritesAdapter.setData(list)
        }
    }
}