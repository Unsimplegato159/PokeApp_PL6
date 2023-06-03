package com.santiagotorres.pokeapp.ui.favorites

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiagotorres.pokeapp.databinding.FragmentFavoritesBinding
import com.santiagotorres.pokeapp.ui.pokeinfo.PokeInfoActivity

class FavoritesFragment : Fragment() {
    private lateinit var favoritesBinding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var favoritesListAdapter: FavoritesListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        favoritesBinding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return favoritesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        initUI()
        observeFavoritePokemonList()
    }

    private fun initUI() {
        favoritesListAdapter = FavoritesListAdapter { id ->
            val intent = Intent(requireContext(), PokeInfoActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        favoritesBinding.pokemonFavoritesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoritesListAdapter
        }
    }

    private fun observeFavoritePokemonList() {
        viewModel.favoritePokemonList.observe(viewLifecycleOwner) { list ->
            favoritesListAdapter.setData(list)
        }
    }
}