package com.santini.covid.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide.init
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.santini.covid.MainViewModel
import com.santini.covid.R
import com.santini.covid.local.converters.AppDataBase
import com.santini.covid.local.dao.CountriesDao
import com.santini.covid.local.entity.Countries
import com.santini.covid.model.countries.CountriesDataResponse
import com.santini.covid.model.countries.Data
import com.santini.covid.model.countryDetails.Timeline
import com.santini.covid.model.countryDetails.countriesDetails
import com.santini.covid.model.responseHandler.Resource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    lateinit var countriesDao : CountriesDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       viewModel.countries.observe(this,countriesObserver)
        countriesDao = AppDataBase.getIntance(this).countriesDao()

        suspend {
            countriesDao.insertCountries(Countries("AF", "Grecia"))
            countriesDao.getCountries()
        }
    }


    private val countriesObserver = Observer< Resource<CountriesDataResponse>>{
        response ->
        when(response.status){
            Resource.Status.ERROR -> {
                Log.e("MainActivity", "The error is: ${response.message}")
            }
            Resource.Status.SUCCESS -> {

                val estado: MutableList<String> = mutableListOf()
                response.data!!.data.let {
                    if (spinnerCounties != null) {
                        spinnerCounties.setTitle("Seleccione un país")
                        spinnerCounties.setPositiveButton("OK")
                         it.forEach{ obj ->
                        estado.add(obj.name)
                        Log.d("countries", "Countries: ${obj.name}")
                        var  adapter = ArrayAdapter(
                            this, android.R.layout.simple_spinner_item, estado
                        )
                        spinnerCounties.adapter = adapter
                    }
                        spinnerCounties.onItemSelectedListener = object :
                            AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>,
                                                        view: View, position: Int, id: Long) {
                                viewModel.obtenerSelectCountries(it[position].code)
                                viewModel.getCountriesDetailsViewModel.observe(this@MainActivity, detailGraphforCountries)
                            }
                            override fun onNothingSelected(parent: AdapterView<*>) {
                                // write code to perform some action
                            }
                        }
                    }
                }

            }


            Resource.Status.LOADING -> {
                Log.d("MainActivity", "Is loading...")


            }

        }

    }

    private val detailGraphforCountries = Observer<Resource<countriesDetails>> { response ->
        when (response.status) {
            Resource.Status.ERROR -> {
                Log.e("DetailActivity", "The error is: ${response.message}")
            }
            Resource.Status.SUCCESS -> {
                Log.d("code", "The data is: ${response.data}\n")
                response?.data?.let {
                  //  texto.text = it.data.timeline[2].confirmed.toString()

                  //  val itemDay: List<Timeline> = it.data.timeline
                    var CountDay :Int =0
                    val estadoDay: MutableList<String> = mutableListOf()
                    var itemNewDeath : MutableList<BarEntry>  = mutableListOf()
                    var itemNewConfirm : MutableList<BarEntry> = mutableListOf()
                    var itemNewRecovered : MutableList<BarEntry> = mutableListOf()

                    if (it.data.latestData.confirmed.toString() != null){
                        sumConfirm.setText(it.data.latestData.confirmed.toString())
                    }else{
                        sumConfirm.setText("0")
                    }
                    if (it.data.latestData.recovered.toString()!= null){
                        sumRecovers.setText(it.data.latestData.recovered.toString())
                    }else{
                        sumRecovers.setText("0")
                    }
                    if (it.data.latestData.deaths.toString() != null){
                        sumDeaths.setText(it.data.latestData.deaths.toString())
                    }else{
                        sumDeaths.setText("0")
                    }
                    if (it.data.latestData.calculated.casesPerMillionPopulation.toString() != null){
                        casesPerMillonPeople.setText(it.data.latestData.calculated.casesPerMillionPopulation.toString())
                    }else{
                        casesPerMillonPeople.setText("0")
                    }



                    /*    for (valorDay in itemDay){
                            estadoDay.add(itemDay[CountDay].date)
                            itemNewDeath.add(BarEntry(CountDay.toFloat(), itemDay[CountDay].newDeaths.toFloat()))

                            itemNewConfirm.add(BarEntry(CountDay.toFloat(),itemDay[CountDay].newConfirmed.toFloat()))
                            itemNewRecovered.add(BarEntry(CountDay.toFloat(),itemDay[CountDay].newRecovered.toFloat()))
                            CountDay++
                        }*/

                    it.data.timeline.let {
                       it.forEach{
                           obj ->
                           if (obj.newConfirmed != null || obj.newConfirmed > 0){
                               estadoDay.add(obj.date)
                               itemNewDeath.add(BarEntry(CountDay.toFloat(), obj.newDeaths.toFloat()))

                               itemNewConfirm.add(BarEntry(CountDay.toFloat(),obj.newConfirmed.toFloat()))
                               itemNewRecovered.add(BarEntry(CountDay.toFloat(),obj.newRecovered.toFloat()))





                           }else{
                               Toast.makeText(this,"no se encontraron datos", Toast.LENGTH_SHORT)

                           }


                           CountDay ++
                       }


                    }

                    val deaths = BarDataSet(itemNewDeath,"Muertos")
                    deaths.color = Color.RED
                    val confirm =BarDataSet(itemNewConfirm,"Confirmados")
                    confirm.color = Color.YELLOW
                    val recover =BarDataSet(itemNewRecovered,"Recuperados")
                    recover.color = Color.GREEN

                    val dataforDay = BarData(confirm,recover,deaths)
                    barChart.data = dataforDay

                    val xAxis:XAxis = barChart.getXAxis()
                    xAxis.valueFormatter = IndexAxisValueFormatter(estadoDay)
                    xAxis.setCenterAxisLabels(true)
                    xAxis.position = XAxis.XAxisPosition.TOP_INSIDE
            //        XAxis.Granularity(1)
                    xAxis.isGranularityEnabled = true
                    barChart.isDragEnabled = true
                    barChart.setVisibleXRangeMaximum(3f)

                    val barSpace = 0.1f
                    val groupSpace = 0.2f

                    dataforDay.barWidth = 0.20f
                    barChart.xAxis.axisMinimum = 0f
                    barChart.groupBars(0f,groupSpace,barSpace)
                    barChart.invalidate()




                }
            }
            Resource.Status.LOADING -> {
                Log.d("DetailActivity", "Is loading...")
            }
        }
    }


}





