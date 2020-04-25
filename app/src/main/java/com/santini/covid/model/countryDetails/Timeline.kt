package com.santini.covid.model.countryDetails


import com.google.gson.annotations.SerializedName

data class Timeline(
    @SerializedName("active")
    val active: Int,
    @SerializedName("confirmed")
    val confirmed: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("is_in_progress")
    val isInProgress: Boolean,
    @SerializedName("new_confirmed")
    val newConfirmed: Int,
    @SerializedName("new_deaths")
    val newDeaths: Int,
    @SerializedName("new_recovered")
    val newRecovered: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)