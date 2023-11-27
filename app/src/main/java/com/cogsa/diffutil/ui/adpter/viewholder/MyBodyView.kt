package com.cogsa.diffutil.ui.adpter.viewholder

internal interface MyBodyView {
    fun setCard(cardType: String, cardNumber: String, money: String)
    fun hideCard()
    fun setOnClickListener(type:String,func:((type:String)->Unit)?)
}