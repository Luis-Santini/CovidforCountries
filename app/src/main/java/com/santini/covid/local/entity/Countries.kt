package com.santini.covid.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "tcountries")
data class Countries(
    @PrimaryKey   val code : String
    , val name : String
)