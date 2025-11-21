package com.example.ccm_ios_2025.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ccm_ios_2025.data.local.dao.AndroidVersionDao
import com.example.ccm_ios_2025.data.model.AndroidVersionEntity

@Database(
    entities = [
        AndroidVersionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {


    abstract fun androidVersionDao(): AndroidVersionDao
}