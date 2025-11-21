package com.example.ccm_ios_2025.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "android_version")
data class AndroidVersionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,


    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "funName")
    val funName: Boolean,
)
