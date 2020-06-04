package com.santini.covid.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santini.covid.local.entity.Countries


@Dao
interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries (countries: Countries)

    @Query("Select * from tcountries")
   suspend fun getCountries(): List<Countries>
}