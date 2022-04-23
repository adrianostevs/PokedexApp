package com.example.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.ApiConfig
import com.example.pokedex.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityViewModel : ViewModel(){

    private val _detailPokemon = MutableLiveData<DetailModel>()
    val detailPokemon : LiveData<DetailModel> = _detailPokemon

    private val _descPokemon = MutableLiveData<FlavorTextEntries>()
    val descPokemon : LiveData<FlavorTextEntries> = _descPokemon

    private val _typePokemon = MutableLiveData<TypeResponse>()
    val typePokemon : LiveData<TypeResponse> = _typePokemon

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getDetailPokemon (pokemon_id: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPokemon(pokemon_id)
        client.enqueue(object: Callback<DetailModel> {
            override fun onResponse(
                call: Call<DetailModel>,
                response: Response<DetailModel>
            ){
                _isLoading.value = false
                Log.d("tess", response.toString())
                if (response.isSuccessful){
                    _detailPokemon.value = response.body()
                    Log.d("tesss", response.body()?.weight.toString())
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

    fun getDescriptionPokemon (pokemon_id: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailPokemon(pokemon_id)
        client.enqueue(object: Callback<FlavorTextEntries> {
            override fun onResponse(
                call: Call<FlavorTextEntries>,
                response: Response<FlavorTextEntries>
            ){
                _isLoading.value = false
                Log.d("tess", response.toString())
                if (response.isSuccessful){
                    _descPokemon.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FlavorTextEntries>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object{
        private val TAG = "DetailActivity"
    }
}