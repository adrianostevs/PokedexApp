package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class AboutModel(
    @field:SerializedName("color")
    val color: Color,

    @field:SerializedName("flavor_text_entries")
    val flavor_text_entries: List<FlavorTextEntries>
)

data class Color(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String
)

data class FlavorTextEntries(
    @field:SerializedName("flavor_text")
    val flavor_text: String,

    @field:SerializedName("language")
    val language: Language
)

data class Language(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String
)
