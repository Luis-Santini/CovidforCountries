package com.santini.covid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.santini.covid.model.Countries.Countries
import com.santini.covid.model.responseHandler.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       viewModel.countries.observe(this,countriesObserver)
    }

    private val countriesObserver = Observer< Resource<Countries>>{
        response ->
        when(response.status){
            Resource.Status.ERROR -> {
                Log.e("MainActivity", "The error is: ${response.message}")
            }
            Resource.Status.SUCCESS -> {
                Log.d("MainActivity", "The data is: ${response.data}\n")
            }
            Resource.Status.LOADING -> {
                Log.d("MainActivity", "Is loading...")
            }


        }

    }
}

