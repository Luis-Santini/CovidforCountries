package com.santini.covid.repository

import android.util.Log
import com.bumptech.glide.load.HttpException
import com.santini.covid.model.countries.CountriesDataResponse
import com.santini.covid.model.countries.Data
import com.santini.covid.model.countryDetails.countriesDetails

import com.santini.covid.model.responseHandler.Resource
import com.santini.covid.model.responseHandler.ResponseHandler
import com.santini.covid.network.CovidInterface

class Repository(
    private val service: CovidInterface,
private val responseHandler: ResponseHandler

) {
    suspend fun getAllCountries(): Resource<CountriesDataResponse> {
        return try {
            val data = service.getAllCountries()
            Log.d("repository", " ${data.toString()}")

            responseHandler.handleSuccess(data)
        }catch (e:HttpException){
            responseHandler.handleException(e)
        }
    }


    suspend fun getCountryDetailsRepo(code : String) : Resource<countriesDetails>{

        return try {
            val data = service.getCountryDetails(code = code)
            responseHandler.handleSuccess(data)
        }catch (e:HttpException){

            responseHandler.handleException(e)
        }


    }
}