package com.example.ccm_ios_2025.architecture

import android.app.Application
import androidx.room.Room
import com.example.ccm_ios_2025.data.local.CustomRoomDatabase


class CustomApplication : Application() {


    companion object {
        lateinit var instance: CustomApplication
    }


    val applicationDatabase: CustomRoomDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CustomRoomDatabase::class.java,
            "MyDatabaseName"
        ).fallbackToDestructiveMigration(false)
            .build()
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
