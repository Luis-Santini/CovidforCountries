package com.santini.covid.repository

import com.bumptech.glide.load.HttpException
import com.santini.covid.model.Countries.Countries
import com.santini.covid.model.responseHandler.Resource
import com.santini.covid.model.responseHandler.ResponseHandler
import com.santini.covid.network.CovidInterface

class Repository(
    private val service: CovidInterface,
private val responseHandler: ResponseHandler

) {
    suspend fun getAllCountries(): Resource<Countries> {
        return try {
            val data = service.getAllCountries()
            responseHandler.handleSuccess(data)
        }catch (e:HttpException){
            responseHandler.handleException(e)
        }

    }
}