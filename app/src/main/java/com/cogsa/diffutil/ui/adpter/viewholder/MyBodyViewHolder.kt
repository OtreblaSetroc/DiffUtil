package com.cogsa.diffutil.ui.adpter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cogsa.diffutil.databinding.MyBodyBinding

internal class MyBodyViewHolder(private val binding: MyBodyBinding) :
    RecyclerView.ViewHolder(binding.root),
    MyBodyView {
    override fun setCard(cardType: String, cardNumber: String, money: String) {
        with(binding) {
            cardView.visibility = View.VISIBLE
            cardNumberTextView.text = cardNumber
            cardTypeTextView.text = cardType
            cardValueTextView.text = money
        }
    }

    override fun hideCard() {
        binding.cardView.visibility = View.GONE
    }

    override fun setOnClickListener(type: String, func: ((type: String) -> Unit)?) {
        binding.cardView.setOnClickListener { func?.invoke(type) }
    }
}