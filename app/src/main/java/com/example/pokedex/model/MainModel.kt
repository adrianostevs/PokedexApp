package com.example.pokedex.model

import com.google.gson.annotations.SerializedName


data class MainModel(

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("next")
    val next: String,

    @field:SerializedName("previous")
    val previous: String,

    @field:SerializedName("results")
    val results: List<Pokemon>
)

data class Pokemon(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String
) {
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }

    fun getId(): String {
        return url.split("/".toRegex()).dropLast(1).last().format("#%03d")
    }
}