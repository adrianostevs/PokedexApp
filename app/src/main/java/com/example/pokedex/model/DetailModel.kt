package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class DetailModel(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("height")
    val height: Int,

    @field:SerializedName("weight")
    val weight: Int,

    @field:SerializedName("base_experience")
    val experience: Int,

    @field:SerializedName("types")
    val types: List<TypeResponse>,

    @field:SerializedName("stats")
    val stats: List<Stats>,

){
    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}

data class Stats(
    @field:SerializedName("base_stat")
    val base_stats: Int,

    @field:SerializedName("stat")
    val stat: Stat
)

data class Stat(
    @field:SerializedName("name")
    val name: String
)

data class TypeResponse(
    @field:SerializedName("slot")
    val slot: Int,

    @field:SerializedName("type")
    val type: Type
)

data class Type(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String
)