package com.santini.covid.model.countries


import com.google.gson.annotations.SerializedName

data class CountriesDataResponse(
    @SerializedName("_cacheHit")
    val cacheHit: Boolean,
    @SerializedName("data")
    val data: List<Data>
)