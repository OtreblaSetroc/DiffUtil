package com.cogsa.diffutil.ui.adpter.presenter

import androidx.recyclerview.widget.RecyclerView
import com.cogsa.diffutil.ui.model.MyData

internal interface MyPresenter {
    var list: List<MyData>
    var diffUtilListener: ((oldSize: Int, newSize: Int) -> Unit)?
    var callback :((type:String)->Unit)?
    fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
    fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
}