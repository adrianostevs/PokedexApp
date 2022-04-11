package com.example.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.databinding.ActivityDetailBinding
import com.example.pokedex.model.DetailModel
import com.example.pokedex.viewmodel.DetailActivityViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailActivityViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailActivityViewModel::class.java)

        detailActivityViewModel.detailPokemon.observe(this){ detailPokemon -> setDetailPokemon(detailPokemon)}

        val name = intent.getStringExtra(NAME)
        if(name!=null){
        detailActivityViewModel.getDetailPokemon(name)
        }
    }

    private fun setDetailPokemon(detailPokemon: DetailModel?) {

        binding.tvId.text = detailPokemon?.getIdString()
        binding.tvNama.text = detailPokemon?.name
        binding.tvTypes1.text = detailPokemon?.types.toString()
        binding.tvTypes2.text = detailPokemon?.types.toString()
        binding.tvAttackStat.text = detailPokemon?.getAttackString()
        binding.tvDefStat.text = detailPokemon?.getDefenseString()
        binding.tvSpeedStat.text = detailPokemon?.getSpeedString()
        binding.tvHpStat.text = detailPokemon?.getHpString()
        binding.tvWeightdesc.text = detailPokemon?.getWeightString()
        binding.tvHeightdesc.text = detailPokemon?.getHeightString()

    }

    companion object{
        const val NAME = "name"
    }
}