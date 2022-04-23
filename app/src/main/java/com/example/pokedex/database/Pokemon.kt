package com.example.pokedex.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class Pokemon(

    @PrimaryKey
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "height")
    var height: Int = 0,

    @ColumnInfo(name = "weight")
    var weight: Int = 0,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "hp")
    var hp: Int = 0,

    @ColumnInfo(name = "attack")
    var attack: Int = 0,

    @ColumnInfo(name = "deff")
    var deff: Int = 0,

    @ColumnInfo(name = "sp_attack")
    var sp_attack: Int = 0,

    @ColumnInfo(name = "sp_deff")
    var sp_deff: Int = 0,

    @ColumnInfo(name = "desc")
    var desc: String? = null,

    @ColumnInfo(name = "type")
    var type: String? = null


) : Parcelable
