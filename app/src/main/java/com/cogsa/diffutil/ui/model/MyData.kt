package com.cogsa.diffutil.ui.model

sealed interface MyData {
    data class Header(val title: String) : MyData
    data class Card(val cardNumber: String, val money: String, val type:CardType) : MyData
    enum class CardType{
        GOLD,
        PLATINUM,
        BLUE
    }
}