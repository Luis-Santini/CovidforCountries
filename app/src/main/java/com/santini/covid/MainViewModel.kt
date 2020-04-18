package com.santini.covid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.santini.covid.model.responseHandler.Resource
import com.santini.covid.repository.Repository
import kotlinx.coroutines.Dispatchers

class MainViewModel (private val repository: Repository) : ViewModel(){

    val countries = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        emit(repository.getAllCountries())
    }
}