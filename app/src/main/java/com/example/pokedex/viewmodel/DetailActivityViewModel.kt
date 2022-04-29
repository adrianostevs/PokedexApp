package com.example.pokedex.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokedex.api.ApiConfig
import com.example.pokedex.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val _detailPokemon = MutableLiveData<DetailModel>()
    val detailPokemon: LiveData<DetailModel> = _detailPokemon

    private val _descPokemon = MutableLiveData<List<FlavorTextEntries>>()
    val descPokemon: LiveData<List<FlavorTextEntries>> = _descPokemon

    private val _typePokemon = MutableLiveData<List<TypeResponse>>()
    val typePokemon: LiveData<List<TypeResponse>> = _typePokemon

    private val _statPokemon = MutableLiveData<List<Stats>>()
    val statPokemon: LiveData<List<Stats>> = _statPokemon

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getDetailPokemon(pokemon_id: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPokemon(pokemon_id)
        client.enqueue(object : Callback<DetailModel> {
            override fun onResponse(
                call: Call<DetailModel>,
                response: Response<DetailModel>
            ) {
                _isLoading.value = false
                Log.d("tess", response.toString())
                if (response.isSuccessful) {
                    _detailPokemon.value = response.body()
                    _statPokemon.value = response.body()?.stats
                    _typePokemon.value = response.body()?.types
                    Log.d("tesss", response.body()?.stats.toString())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailModel>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDescriptionPokemon(pokemon_id: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailPokemon(pokemon_id)
        client.enqueue(object : Callback<AboutModel> {
            override fun onResponse(
                call: Call<AboutModel>,
                response: Response<AboutModel>
            ) {
                _isLoading.value = false
                Log.d("tess", response.toString())
                if (response.isSuccessful) {
                    _descPokemon.value = response.body()?.flavor_text_entries
                    Log.d("tex", response.body()?.flavor_text_entries.toString())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<AboutModel>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

    }

    companion object {
        private const val TAG = "DetailActivity"
    }
}