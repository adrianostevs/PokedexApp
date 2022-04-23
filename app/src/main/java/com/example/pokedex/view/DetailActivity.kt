package com.example.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokedex.databinding.ActivityDetailBinding
import com.example.pokedex.model.*
import com.example.pokedex.viewmodel.DetailActivityViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailActivityViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailActivityViewModel::class.java)

        detailActivityViewModel.detailPokemon.observe(this){ detailPokemon -> setDetailPokemon(detailPokemon)}
        detailActivityViewModel.typePokemon.observe(this){typePokemon -> setPokemonType(typePokemon)}
        detailActivityViewModel.descPokemon.observe(this){descPokemon -> setDescPokemon(descPokemon)}


        val name = intent.getStringExtra(NAME)
        if(name!=null){
            detailActivityViewModel.getDetailPokemon(name)
            detailActivityViewModel.getDescriptionPokemon(name)
            Log.d("sss", name.toString())
        }
    }

    private fun setDescPokemon(descPokemon: FlavorTextEntries) {
        binding.tvDesc.text = descPokemon.flavor_text
    }

    private fun setDetailPokemon(detailPokemon: DetailModel?) {
        binding.tvId.text = detailPokemon?.getIdString()
        binding.tvNama.text = detailPokemon?.name
        binding.tvAttackStat.text = detailPokemon?.getAttackString()
        binding.tvDefStat.text = detailPokemon?.getDefenseString()
        binding.tvSpeedStat.text = detailPokemon?.getSpeedString()
        binding.tvHpStat.text = detailPokemon?.getHpString()
        binding.tvWeightdesc.text = detailPokemon?.getWeightString()
        binding.tvHeightdesc.text = detailPokemon?.getHeightString()
        Glide.with(this)
            .load(detailPokemon?.getImageUrl())
            .into(binding.ivAvatar)
    }

    private fun setPokemonType(typePokemon: TypeResponse){
        binding.tvTypes1.text = typePokemon.type.name
        binding.tvTypes2.text = typePokemon.type.name
    }

    companion object{
        const val NAME = "name"
    }
}