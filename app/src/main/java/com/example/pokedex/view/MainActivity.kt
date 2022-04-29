package com.example.pokedex.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.adapter.ListPokemonAdapter
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val listPokemonAdapter = ListPokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainActivityViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainActivityViewModel::class.java]
        mainActivityViewModel.getListPokemon(30, 0)
        binding.rvList.adapter = listPokemonAdapter

        val layoutManager = GridLayoutManager(this, 3)
        binding.rvList.layoutManager = layoutManager
        val itemDecorationHorizontal = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        binding.rvList.addItemDecoration(itemDecorationHorizontal)
        val itemDecorationVertical = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvList.addItemDecoration(itemDecorationVertical)


        mainActivityViewModel.showPokemon.observe(this) { results -> setPokemon(results) }
        mainActivityViewModel.isLoading.observe(this){showLoading(it)}

    }

    private fun setPokemon(results: List<Pokemon>) {
        listPokemonAdapter.setData(results)
        listPokemonAdapter.setOnItemClickCallback(object : ListPokemonAdapter.OnItemClickCallback {
            override fun onItemClicked(pokes: Pokemon) {
                val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveWithObjectIntent.putExtra(DetailActivity.NAME, pokes.name)
                moveWithObjectIntent.putExtra(DetailActivity.IMAGE, pokes.getImageUrl())
                moveWithObjectIntent.putExtra(DetailActivity.ID, pokes.getId())
                startActivity(moveWithObjectIntent)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}
