package com.example.ccm_ios_2025.data.repository

import com.example.ccm_ios_2025.architecture.CustomApplication
import com.example.ccm_ios_2025.data.mapping.toDataObject
import com.example.ccm_ios_2025.data.mapping.toRoomObject
import com.example.ccm_ios_2025.data.model.AndroidVersionEntity
import com.example.ccm_ios_2025.data.model.MyAndroidModelData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidVersionRepository {

    private val androidVersionDao = CustomApplication.instance.applicationDatabase.androidVersionDao()


    fun selectAllAndroidVersion(): Flow<List<MyAndroidModelData>> {
        return androidVersionDao.selectAll().map { androidVersionEntity: List<AndroidVersionEntity> ->
            androidVersionEntity.toDataObject()
        }
    }


    fun insertAndroidVersion(myAndroidModelData: MyAndroidModelData) {
        androidVersionDao.insert(myAndroidModelData.toRoomObject())
    }


    fun deleteAllAndroidVersion() {
        androidVersionDao.deleteAll()
    }

    fun populateMyList(): List<MyAndroidModelData> {
        return listOf(
            MyAndroidModelData(
                versionName = "HoneyComb",
                versionCode = "3.0",
                image="https://media1.tenor.com/m/lztbLMEBDs4AAAAd/sonic-boom-knuckles.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Ice Cream Sandwich",
                versionCode = "4.0",
                image = "https://media1.tenor.com/m/lztbLMEBDs4AAAAd/sonic-boom-knuckles.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Ice Cream Sandwich",
                versionCode = "4.0.1",
                image = "https://media1.tenor.com/m/lztbLMEBDs4AAAAd/sonic-boom-knuckles.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Ice Cream Sandwich",
                versionCode = "4.0.2",
                image = "https://media1.tenor.com/m/lztbLMEBDs4AAAAd/sonic-boom-knuckles.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Ice Cream Sandwich",
                versionCode = "4.0.3",
                image = "https://media1.tenor.com/m/lztbLMEBDs4AAAAd/sonic-boom-knuckles.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Jelly Bean",
                versionCode = "4.1",
                image = "https://media1.tenor.com/m/lztbLMEBDs4AAAAd/sonic-boom-knuckles.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Jelly Bean",
                versionCode = "4.2",
                image = "https://media1.tenor.com/m/lztbLMEBDs4AAAAd/sonic-boom-knuckles.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Jelly Bean",
                versionCode = "4.3",
                image = "https://media1.tenor.com/m/lztbLMEBDs4AAAAd/sonic-boom-knuckles.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Kitkat",
                versionCode = "4.4",
                image = "https://media1.tenor.com/m/BotFj4frgNkAAAAd/sonic-boom-knuckles.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Lollipop",
                versionCode = "5.0",
                image = "https://media1.tenor.com/m/vzOz8IrE8TYAAAAd/knuckles-approved.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Lollipop",
                versionCode = "5.1",
                image = "https://media1.tenor.com/m/vzOz8IrE8TYAAAAd/knuckles-approved.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Marshmallow",
                versionCode = "6.0",
                image = "https://media1.tenor.com/m/lztbLMEBDs4AAAAd/sonic-boom-knuckles.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Nougat",
                versionCode = "7.0",
                image = "https://media1.tenor.com/m/BotFj4frgNkAAAAd/sonic-boom-knuckles.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Oreo",
                versionCode = "8.0",
                image = "https://media1.tenor.com/m/vzOz8IrE8TYAAAAd/knuckles-approved.gif",
                funName=false,
            ), MyAndroidModelData(
                versionName = "Oreo",
                versionCode = "8.1",
                image = "https://media1.tenor.com/m/vzOz8IrE8TYAAAAd/knuckles-approved.gif",
                funName=false,
            )
        )
    }
}