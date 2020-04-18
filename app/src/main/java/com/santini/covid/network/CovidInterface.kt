package com.santini.covid.network

import com.santini.covid.model.Countries.Countries
import retrofit2.http.GET

interface CovidInterface {

@GET ("countries")
suspend fun getAllCountries(): Countries

   /* @GET("countries/AR")
    suspend fun getCountryDetails(@Query("code") code: String): List<Timeline>*/
}