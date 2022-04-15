package com.example.pokedex.api

import com.example.pokedex.model.DetailModel
import com.example.pokedex.model.MainModel
import com.example.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    fun getListPokemon(
    ): Call<MainModel>

    @GET("pokemon/{pokemon_id}")
    fun getPokemon(
        @Path("pokemon_id") pokemon_id: String
    ): Call<MainModel>

    @GET("pokemon-species/{pokemon_id}")
    fun getDetailPokemon(
        @Path("pokemon_id") pokemon_id: String
    ): Call<DetailModel>
}