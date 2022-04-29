package com.example.pokedex.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.adapter.ListStatAdapter
import com.example.pokedex.databinding.ActivityDetailBinding
import com.example.pokedex.model.DetailModel
import com.example.pokedex.model.FlavorTextEntries
import com.example.pokedex.model.Stats
import com.example.pokedex.model.TypeResponse
import com.example.pokedex.viewmodel.DetailActivityViewModel
import com.example.tipedex.adapter.ListTipeAdapter

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val listStatAdapter = ListStatAdapter()
    private val listTipeAdapter = ListTipeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvType.adapter = listTipeAdapter
        val layoutManger = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvType.layoutManager = layoutManger

        binding.rvDetail.adapter = listStatAdapter
        val layoutManager = LinearLayoutManager(this)
        binding.rvDetail.layoutManager = layoutManager

        val detailActivityViewModel = ViewModelProvider(this)[DetailActivityViewModel::class.java]

        detailActivityViewModel.detailPokemon.observe(this) { detailPokemon ->
            setDetailPokemon(
                detailPokemon
            )
        }
        detailActivityViewModel.statPokemon.observe(this) { pokes -> setStatPokemon(pokes) }
        detailActivityViewModel.typePokemon.observe(this) { typePokemon ->
            setPokemonType(
                typePokemon
            )
        }
        detailActivityViewModel.descPokemon.observe(this) { descPokemon ->
            setDescPokemon(
                descPokemon
            )
        }

        detailActivityViewModel.isLoading.observe(this) { showLoading(it) }
        val name = intent.getStringExtra(NAME)

        if (name != null) {
            detailActivityViewModel.getDetailPokemon(name)
            detailActivityViewModel.getDescriptionPokemon(name)
            Log.d("sss", name.toString())
        }

    }

    private fun setDescPokemon(descPokemon: List<FlavorTextEntries>) {
        val listDesc = ArrayList<String>()
        for (flavor_text in descPokemon) {
            listDesc.add(
                """
                ${flavor_text.flavor_text}
                """.trim()
            )
        }

        val description = descPokemon.filter { it.language.name == "en" }.distinct().take(3)
            .joinToString(" ") { it.flavor_text }.replace("[\\n\\t\\f]".toRegex(), " ")
        binding.tvDesc.text =
            description
        Log.d("texxx", listDesc.distinct().take(3).joinToString())
    }

    private fun setPokemonType(typePokemon: List<TypeResponse>) {
        listTipeAdapter.setData(typePokemon)
        Log.d("tipe", typePokemon.toString())

        val listTipe = ArrayList<String>()
        for (name in typePokemon) {
            listTipe.add(
                """
                ${name.type.name}
                """.trim()
            )
        }

        when {
            listTipe.first() == "grass" -> {
                binding.ivBase.setBackgroundResource(R.color.grass)
            }
            listTipe.first() == "poison" -> {
                binding.ivBase.setBackgroundResource(R.color.poison)
            }
            listTipe.first() == "fire" -> {
                binding.ivBase.setBackgroundResource(R.color.fire)
            }
            listTipe.first() == "normal" -> {
                binding.ivBase.setBackgroundResource(R.color.normal)
            }
            listTipe.first() == "flying" -> {
                binding.ivBase.setBackgroundResource(R.color.flying)
            }
            listTipe.first() == "rock" -> {
                binding.ivBase.setBackgroundResource(R.color.rock)
            }
            listTipe.first() == "water" -> {
                binding.ivBase.setBackgroundResource(R.color.water)
            }
            listTipe.first() == "ice" -> {
                binding.ivBase.setBackgroundResource(R.color.ice)
            }
            listTipe.first() == "ghost" -> {
                binding.ivBase.setBackgroundResource(R.color.ghost)
            }
            listTipe.first() == "steel" -> {
                binding.ivBase.setBackgroundResource(R.color.steel)
            }
            listTipe.first() == "physic" -> {
                binding.ivBase.setBackgroundResource(R.color.physic)
            }
            listTipe.first() == "dark" -> {
                binding.ivBase.setBackgroundResource(R.color.dark)
            }
            listTipe.first() == "fairy" -> {
                binding.ivBase.setBackgroundResource(R.color.fairy)
            }
            listTipe.first() == "fighting" -> {
                binding.ivBase.setBackgroundResource(R.color.fighting)
            }
            listTipe.first() == "ground" -> {
                binding.ivBase.setBackgroundResource(R.color.ground)
            }
            listTipe.first() == "bug" -> {
                binding.ivBase.setBackgroundResource(R.color.bug)
            }
            listTipe.first() == "electric" -> {
                binding.ivBase.setBackgroundResource(R.color.electric)
            }
            listTipe.first() == "dragon" -> {
                binding.ivBase.setBackgroundResource(R.color.dragon)
            }
        }

    }


    private fun setDetailPokemon(detailPokemon: DetailModel?) {
        binding.tvId.text = detailPokemon?.getIdString()
        binding.tvNama.text = detailPokemon?.name
        binding.tvWeight.text = detailPokemon?.getWeightString()
        binding.tvHeight.text = detailPokemon?.getHeightString()
        Glide.with(this)
            .load(detailPokemon?.getImageUrl())
            .into(binding.ivAvatar)
    }

    private fun setStatPokemon(pokes: List<Stats>) {
        listStatAdapter.setData(pokes)
        Log.d("list", pokes.toString())
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val NAME = "name"
        const val IMAGE = "image"
        const val ID = "id"
    }
}