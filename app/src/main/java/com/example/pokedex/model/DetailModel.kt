package com.example.pokedex.model

import kotlin.random.Random
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

    val hp: Int = Random.nextInt(maxHp),
    val attack: Int = Random.nextInt(maxAttack),
    val defense: Int = Random.nextInt(maxDefense),
    val speed: Int = Random.nextInt(maxSpeed),
){
    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = "$hp/$maxHp"
    fun getAttackString(): String = "$attack/$maxAttack"
    fun getDefenseString(): String = "$defense/$maxDefense"
    fun getSpeedString(): String = "$speed/$maxSpeed"
    fun getImageUrl(): String {
        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
        return url
    }

    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
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