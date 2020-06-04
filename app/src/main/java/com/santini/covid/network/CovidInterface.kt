package com.santini.covid.network


import com.santini.covid.model.countries.CountriesDataResponse
import com.santini.covid.model.countryDetails.Timeline
import com.santini.covid.model.countryDetails.countriesDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidInterface {

@GET ("countries")
suspend fun getAllCountries(): CountriesDataResponse

    @GET("countries/{code}")
    suspend fun getCountryDetails(@Path("code") code: String): countriesDetails
}