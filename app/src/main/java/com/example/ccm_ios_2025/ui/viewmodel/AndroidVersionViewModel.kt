package com.example.ccm_ios_2025.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ccm_ios_2025.data.model.MyAndroidModelData
import com.example.ccm_ios_2025.data.repository.AndroidVersionRepository
import com.example.ccm_ios_2025.ui.model.ItemUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random

class AndroidVersionViewModel : ViewModel() {

    private val _androidVersionList = MutableStateFlow<List<ItemUi>>(emptyList())
    private val androidVersionRepository: AndroidVersionRepository by lazy { AndroidVersionRepository() }

    // Pool d’images random
    private val imagePool = listOf(
        "https://media1.tenor.com/m/lztbLMEBDs4AAAAd/sonic-boom-knuckles.gif",
        "https://media1.tenor.com/m/BotFj4frgNkAAAAd/sonic-boom-knuckles.gif",
        "https://media1.tenor.com/m/vzOz8IrE8TYAAAAd/knuckles-approved.gif"
    )

    val androidVersionList: StateFlow<List<ItemUi>> =
        androidVersionRepository.selectAllAndroidVersion()
            .map { androidObjectEntities: List<MyAndroidModelData> ->
                androidObjectEntities
                    .groupBy { androidModelData -> androidModelData.versionName }
                    .flatMap { (versionName, itemsOfGroup) ->
                        buildList {
                            add(
                                ItemUi.Header(
                                    title = versionName,
                                    image = itemsOfGroup.first().image
                                )
                            )
                            addAll(
                                itemsOfGroup.map { each ->
                                    ItemUi.Item(
                                        title = each.versionCode,
                                        image = each.image,
                                    )
                                }
                            )
                            add(
                                ItemUi.Footer(
                                    numberOf = itemsOfGroup.size.toString()
                                )
                            )
                        }
                    }
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.Lazily
            )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _androidVersionList.emit(populateMyList())
        }
    }

    private fun populateMyList(): List<ItemUi> {
        val listOfResult = mutableListOf<ItemUi>()

        androidVersionRepository.populateMyList()
            .groupBy { item -> item.versionName }
            .forEach { group ->
                listOfResult.add(
                    ItemUi.Header(
                        title = group.key,
                        image = group.value.first().image
                    )
                )

                listOfResult.addAll(
                    group.value.map {
                        ItemUi.Item(
                            title = it.versionName,
                            image = it.image
                        )
                    }
                )

                listOfResult.add(
                    ItemUi.Footer(numberOf = group.value.size.toString())
                )
            }

        return listOfResult
    }

    fun insertAndroidVersion() {
        viewModelScope.launch(Dispatchers.IO) {

            val random = Random.nextInt(0, 10)
            val randomCode = Random.nextInt(0, 10)
            val randomImage = imagePool.random()
            val randomBool = Random.nextBoolean()

            androidVersionRepository.insertAndroidVersion(
                MyAndroidModelData(
                    versionName = "Android $random",
                    versionCode = "$randomCode",
                    image = randomImage,
                    funName = randomBool
                )
            )
        }
    }

    fun deleteAllAndroidVersion() {
        viewModelScope.launch(Dispatchers.IO) {
            androidVersionRepository.deleteAllAndroidVersion()
        }
    }
}
