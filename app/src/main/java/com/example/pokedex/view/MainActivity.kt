package com.example.pokedex.view

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.R
import com.example.pokedex.adapter.ListPokemonAdapter
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.model.MainModel
import com.example.pokedex.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val listPokemonAdapter = ListPokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvList.adapter = listPokemonAdapter

        val layoutManager = GridLayoutManager(this, 3)
        binding.rvList.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvList.addItemDecoration(itemDecoration)

        val mainActivityViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainActivityViewModel::class.java)
        mainActivityViewModel.showPokemon.observe(this) { results -> setPokemon(results) }
        mainActivityViewModel.searchPokemon.observe(this){ search -> searchPokemon(search)}
        mainActivityViewModel.isLoading.observe(this){showLoading(it)}

        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = binding.svCari
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.cari_pokemon)
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(pokemon_id: String): Boolean {
                Toast.makeText(this@MainActivity, pokemon_id, Toast.LENGTH_SHORT).show()
                mainActivityViewModel.getSearchPokemon(pokemon_id)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }


    private fun searchPokemon(search: MainModel) {
        listPokemonAdapter.setData(search)
        listPokemonAdapter.setOnItemClickCallback(object : ListPokemonAdapter.OnItemClickCallback {
            override fun onItemClicked(pokemon: MainModel) {
                showSelectedUser(pokemon)
                val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveWithObjectIntent.putExtra(DetailActivity.USERNAME, pokemon.name)
                startActivity(moveWithObjectIntent)
            }
        })
    }

    private fun setPokemon(results: MainModel) {
        listPokemonAdapter.setData(results)
        listPokemonAdapter.setOnItemClickCallback(object : ListPokemonAdapter.OnItemClickCallback {
            override fun onItemClicked(pokemon: MainModel) {
                showSelectedUser(pokemon)
                val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveWithObjectIntent.putExtra(DetailActivity.NAME, pokemon.name)
                startActivity(moveWithObjectIntent)
            }
        })
    }

    private fun showSelectedUser(pokemon: MainModel) {
        Toast.makeText(this, "Anda memilih " + pokemon.name, Toast.LENGTH_SHORT).show()
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}
