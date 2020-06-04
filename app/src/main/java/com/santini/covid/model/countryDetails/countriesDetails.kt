package com.santini.covid.model.countryDetails


import com.google.gson.annotations.SerializedName

data class countriesDetails(
    @SerializedName("_cacheHit")
    val cacheHit: Boolean,
    @SerializedName("data")
    val data: Data
)