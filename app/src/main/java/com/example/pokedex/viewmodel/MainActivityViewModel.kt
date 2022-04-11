package com.example.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.ApiConfig
import com.example.pokedex.model.MainModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    private val _showPokemon = MutableLiveData<MainModel>()
    val showPokemon : LiveData<MainModel> = _showPokemon

    private val _searchPokemon = MutableLiveData<MainModel>()
    val searchPokemon : LiveData<MainModel> = _searchPokemon

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getListPokemon (offset: Int){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListPokemon(offset)
        client.enqueue(object: Callback<MainModel> {
            override fun onResponse(
                call: Call<MainModel>,
                response: Response<MainModel>
            ){
                _isLoading.value = false
                if (response.isSuccessful){
                    _showPokemon.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MainModel>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getSearchPokemon (pokemon_id: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPokemon(pokemon_id)
        client.enqueue(object: Callback<MainModel> {
            override fun onResponse(
                call: Call<MainModel>,
                response: Response<MainModel>
            ){
                _isLoading.value = false
                if (response.isSuccessful){
                    _searchPokemon.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MainModel>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object{
        private const val TAG = "MainActivityViewModel"
    }


}