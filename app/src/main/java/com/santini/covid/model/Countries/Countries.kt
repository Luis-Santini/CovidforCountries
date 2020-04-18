package com.santini.covid.model.Countries


import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("_cacheHit")
    val cacheHit: Boolean,
    @SerializedName("data")
    val `data`: List<Data>
)