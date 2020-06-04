package com.santini.covid.local.converters
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.santini.covid.local.dao.CountriesDao
import com.santini.covid.local.entity.Countries

@Database(entities = [Countries::class], version = 1)
 abstract class AppDataBase: RoomDatabase (){
    abstract fun countriesDao() : CountriesDao

    companion object{
        private var INSTANCE : AppDataBase? = null
        fun getIntance(context: Context):AppDataBase{
            if (INSTANCE == null){
              INSTANCE =  Room.databaseBuilder(

                    context.applicationContext,
                    AppDataBase::class.java, "coviddata-base"
                ).build()
            }
            return INSTANCE!!
        }
    }
}
