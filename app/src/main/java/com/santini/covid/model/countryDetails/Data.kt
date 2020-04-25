package com.santini.covid.model.countryDetails


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("code")
    val code: String,
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("latest_data")
    val latestData: LatestData,
    @SerializedName("name")
    val name: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("timeline")
    val timeline: List<Timeline>,
    @SerializedName("today")
    val today: Today,
    @SerializedName("updated_at")
    val updatedAt: String
)