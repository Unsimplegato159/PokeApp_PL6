package com.santiagotorres.pokeapp.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiagotorres.pokeapp.R
import com.santiagotorres.pokeapp.databinding.FragmentPokemonListBinding
import com.santiagotorres.pokeapp.ui.pokeinfo.PokeInfoActivity

class PokemonListFragment : Fragment() {

    private lateinit var pokemonlistBinding: FragmentPokemonListBinding
    private lateinit var viewModel: PokemonListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pokemonlistBinding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return pokemonlistBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[PokemonListViewModel::class.java]
        val buttonUp = view.findViewById<Button>(R.id.button_up)

        buttonUp.setOnClickListener {
            pokemonlistBinding.pokemonlistRecyclerView.smoothScrollToPosition(0)
        }

        initUI()
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