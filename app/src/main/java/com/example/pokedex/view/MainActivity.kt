package com.example.pokedex.view

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.R
import com.example.pokedex.adapter.ListPokemonAdapter
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val listPokemonAdapter = ListPokemonAdapter()
    private val PokemonArrayList = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainActivityViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getListPokemon(50,1)
       binding.rvList.adapter = listPokemonAdapter

        val layoutManager = GridLayoutManager(this, 3)
        binding.rvList.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvList.addItemDecoration(itemDecoration)


        mainActivityViewModel.showPokemon.observe(this) { results -> setPokemon(results) }
        mainActivityViewModel.isLoading.observe(this){showLoading(it)}


        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = binding.svCari
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.cari_pokemon)
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(pokemon_id: String): Boolean {
                Toast.makeText(this@MainActivity, pokemon_id, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })
    }

    private fun setPokemon(results: List<Pokemon>) {
        listPokemonAdapter.setData(results)
        listPokemonAdapter.setOnItemClickCallback(object : ListPokemonAdapter.OnItemClickCallback {
            override fun onItemClicked(pokemon: Pokemon) {
                showSelectedUser(pokemon)
                val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveWithObjectIntent.putExtra(DetailActivity.NAME, pokemon.name)
                startActivity(moveWithObjectIntent)
            }
        })
    }

    private fun showSelectedUser(pokemon: Pokemon) {
        Toast.makeText(this, "Anda memilih " + pokemon.name, Toast.LENGTH_SHORT).show()
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}
