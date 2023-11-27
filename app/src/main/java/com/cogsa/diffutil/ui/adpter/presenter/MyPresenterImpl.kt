package com.cogsa.diffutil.ui.adpter.presenter

import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.cogsa.diffutil.ui.adpter.viewholder.MyBodyView
import com.cogsa.diffutil.ui.adpter.viewholder.MyHeaderView
import com.cogsa.diffutil.ui.model.MyData
import com.cogsa.diffutil.ui.model.MyData.Card
import com.cogsa.diffutil.ui.model.MyData.Header

/**The idea of the presenter is to split the business ui Logic. You can make the UT  */
internal class MyPresenterImpl : MyPresenter {
    private var oldList: List<MyData> = emptyList()
    override var list: List<MyData> = emptyList()
        set(value) {
            oldList = field
            field = value
            diffUtilListener?.invoke(oldList.size, field.size)
        }
    override var callback: ((type: String) -> Unit)? = null

    override var diffUtilListener: ((oldSize: Int, newSize: Int) -> Unit)? = null
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = list[newItemPosition]
        return when {
            oldItem is Header && newItem is Header -> {
                headerAreItemsTheSame(oldItem, newItem)
            }

            oldItem is Card && newItem is Card -> {
                cardAreItemsTheSame(oldItem, newItem)
            }

            else -> false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = list[newItemPosition]
        return when {
            oldItem is Header && newItem is Header -> {
                headerAreContentTheSame(oldItem, newItem)
            }

            oldItem is Card && newItem is Card -> {
                cardAreContentTheSame(oldItem, newItem)
            }

            else -> false
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when {
            holder is MyHeaderView && item is Header -> {
                setHeaderData(holder, item)
            }

            holder is MyBodyView && item is Card -> {
                setBodyData(holder, item)
            }
        }
    }

    @VisibleForTesting
    fun setHeaderData(view: MyHeaderView, data: Header) {
        with(view) {
            if (data.title.isNotEmpty()) {
                setTitle(title = data.title)
            } else {
                hideTitle()
            }
        }
    }

    @VisibleForTesting
    fun setBodyData(view: MyBodyView, data: Card) {
        with(view) {
            if (data.cardNumber.isNotEmpty() && data.money.isNotEmpty()) {
                setCard(cardType = data.type.name, cardNumber = data.cardNumber, money = data.money)
                setOnClickListener(data.type.name, callback)

            } else {
                hideCard()
            }
        }
    }


    /** region areItemsTheSame */
    @VisibleForTesting
    fun headerAreItemsTheSame(oldItem: Header, newItem: Header): Boolean =
        oldItem === newItem

    @VisibleForTesting
    fun cardAreItemsTheSame(oldItem: Card, newItem: Card): Boolean =
        oldItem === newItem
    /** endregion areItemsThe */

    /** region areContentTheSame */
    @VisibleForTesting
    fun headerAreContentTheSame(oldItem: Header, newItem: Header): Boolean =
        oldItem.title == newItem.title

    /** Here is very important to choose which parameter or parameters will tell us
     * if recyclerview need to update when that data was changed.
     * I mean, we have  two properties, description and money, only when the money is different
     * the RV will be updated
     * */
    @VisibleForTesting
    fun cardAreContentTheSame(oldItem: Card, newItem: Card): Boolean =
        oldItem.money == newItem.money
    /** endregion reContentTheSame */
}