package com.cogsa.diffutil.ui.adpter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cogsa.diffutil.databinding.MyHeaderBinding

internal class MyHeaderViewHolder(private val binding: MyHeaderBinding) :
    RecyclerView.ViewHolder(binding.root), MyHeaderView {
    override fun setTitle(title: String) {
        binding.myTitleTextView.text=title
    }

    override fun hideTitle() {
        binding.myTitleTextView.visibility = View.GONE
    }
}