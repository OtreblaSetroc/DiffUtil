package com.cogsa.diffutil.ui.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cogsa.diffutil.R
import com.cogsa.diffutil.databinding.MyBodyBinding
import com.cogsa.diffutil.databinding.MyHeaderBinding
import com.cogsa.diffutil.ui.adpter.presenter.MyPresenter
import com.cogsa.diffutil.ui.adpter.viewholder.MyBodyViewHolder
import com.cogsa.diffutil.ui.adpter.viewholder.MyHeaderViewHolder
import com.cogsa.diffutil.ui.model.MyData.Card
import com.cogsa.diffutil.ui.model.MyData.Header

internal class MyAdapter(private val presenter: MyPresenter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    init {
        presenter.diffUtilListener = ::updateList
    }

    override fun getItemViewType(position: Int): Int {
        return  when(presenter.list[position]){
                is Header -> R.layout.my_header
                is Card -> R.layout.my_body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      return  when(viewType){
            R.layout.my_header -> createHeaderViewHolder(parent)
            R.layout.my_body -> createBodyViewHolder(parent)
            else -> createHeaderViewHolder(parent)
        }
    }

    override fun getItemCount(): Int = presenter.list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        presenter.onBindViewHolder(holder, position)
    }

    /** This function is called very time when the list i updated **/
    private fun updateList(oldListSize: Int, newSizeList: Int) {
        val diffUtil = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldListSize

            override fun getNewListSize(): Int = newSizeList

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                presenter.areItemsTheSame(oldItemPosition, newItemPosition)

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                presenter.areContentsTheSame(oldItemPosition, newItemPosition)

        }
        DiffUtil.calculateDiff(diffUtil)
    }

    private fun createHeaderViewHolder(parent: ViewGroup): MyHeaderViewHolder {
        val binding = MyHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHeaderViewHolder(binding)
    }

    private fun createBodyViewHolder(parent: ViewGroup): MyBodyViewHolder {
        val binding = MyBodyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyBodyViewHolder(binding)
    }
}