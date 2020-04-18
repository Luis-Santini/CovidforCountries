package com.santini.covid.model.Countries


import com.google.gson.annotations.SerializedName

data class Today(
    @SerializedName("confirmed")
    val confirmed: Int,
    @SerializedName("deaths")
    val deaths: Int
)