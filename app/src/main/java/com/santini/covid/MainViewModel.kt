package com.santini.covid

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.santini.covid.local.converters.AppDataBase
import com.santini.covid.local.dao.CountriesDao
import com.santini.covid.local.entity.Countries
import com.santini.covid.model.countries.Data
import com.santini.covid.model.responseHandler.Resource
import com.santini.covid.repository.Repository
import kotlinx.coroutines.Dispatchers

class MainViewModel (private val repository: Repository) : ViewModel(){
private val _CodeLiveData = MutableLiveData<String>()
    lateinit var countriesDao : CountriesDao

    val countries = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        emit(repository.getAllCountries())
    }

    fun obtenerSelectCountries (code : String){
      if (_CodeLiveData.value != code){
    _CodeLiveData.value = code

    return
     }

    }
    val getCountriesDetailsViewModel = _CodeLiveData.switchMap { repoCode ->
        liveData(Dispatchers.IO){
            emit(Resource.loading(null))
            emit(repository.getCountryDetailsRepo(repoCode))

        }
    }
}