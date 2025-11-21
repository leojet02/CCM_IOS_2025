package com.example.ccm_ios_2025.ui.model;


sealed interface ItemUi {


    data class Item(
        val title: String,
        val image: String,
    ) : ItemUi


    data class Header(
        val title: String,
        val image: String,
    ) : ItemUi

    data class Footer(
        val numberOf: String,
    ) : ItemUi

}
