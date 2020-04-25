package com.santini.covid.model.countryDetails


import com.google.gson.annotations.SerializedName

data class Calculated(
    @SerializedName("cases_per_million_population")
    val casesPerMillionPopulation: Int,
    @SerializedName("death_rate")
    val deathRate: Double,
    @SerializedName("recovered_vs_death_ratio")
    val recoveredVsDeathRatio: Any,
    @SerializedName("recovery_rate")
    val recoveryRate: Double
)