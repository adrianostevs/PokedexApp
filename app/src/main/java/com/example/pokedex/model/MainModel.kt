package com.example.pokedex.model

import com.google.gson.annotations.SerializedName


data class MainModel(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String ) {

	fun getImageUrl(): String {
		val index = url.split("/".toRegex()).dropLast(1).last()
		return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
	}

	fun getId(): String {
		val index = url.split("/".toRegex()).dropLast(1).last()
		return index
	}
}