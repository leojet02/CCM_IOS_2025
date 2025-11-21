package com.example.ccm_ios_2025.data.mapping

import com.example.ccm_ios_2025.data.model.AndroidVersionEntity
import com.example.ccm_ios_2025.data.model.MyAndroidModelData

fun MyAndroidModelData.toRoomObject(): AndroidVersionEntity {
    return AndroidVersionEntity(
        name = versionName,
        code = versionCode,
        image = image,
        funName = funName,
    )
}


fun List<AndroidVersionEntity>.toDataObject(): List<MyAndroidModelData> {
    return this.map { eachItem ->
        MyAndroidModelData(
            versionCode = eachItem.code,
            versionName = eachItem.name,
            image = eachItem.image,
            funName = eachItem.funName,
        )
    }
}


