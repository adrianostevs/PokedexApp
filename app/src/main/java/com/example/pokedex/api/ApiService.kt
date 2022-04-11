package com.example.pokedex.api

import com.example.pokedex.model.DetailModel
import com.example.pokedex.model.MainModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon?limit=30&offset={offset}")
    fun getListPokemon(
        @Path("offset") offset: Int
    ): Call <MainModel>

    @GET("pokemon/{pokemon_id}")
    fun getPokemon(
        @Query("pokemon_id") pokemon_id: String
    ): Call<MainModel>

    @GET("pokemon-species/{pokemon_id}")
    fun getDetailPokemon(
        @Path("pokemon_id") pokemon_id: String
    ): Call<DetailModel>
}