package com.cogsa.diffutil.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cogsa.diffutil.R
import com.cogsa.diffutil.databinding.ActivityMainBinding
import com.cogsa.diffutil.ui.adpter.MyAdapter
import com.cogsa.diffutil.ui.adpter.presenter.MyPresenter
import com.cogsa.diffutil.ui.adpter.presenter.MyPresenterImpl
import com.cogsa.diffutil.ui.model.MyData
import com.cogsa.diffutil.ui.model.MyData.Card
import com.cogsa.diffutil.ui.model.MyData.Header

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MyPresenter
    private lateinit var adapter: MyAdapter
    private var myList = mutableListOf<MyData>()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setView()
        createDummyData()
    }

    private fun setView() {
        presenter = MyPresenterImpl().apply {
            callback = ::onClicked

        }
        adapter = MyAdapter(presenter)
        with(binding) {
            myRecyclerView.adapter = adapter
            myRecyclerView.setHasFixedSize(true)
        }
    }

    private fun createDummyData() {
        myList.apply {
            add(Header("My Card"))
            add(
                Card(
                    cardNumber = getString(R.string.card_description, "1234"),
                    money = getString(R.string.card_value, 500.5),
                    type = MyData.CardType.PLATINUM
                )
            )
            add(
                Card(
                    cardNumber = getString(R.string.card_description, "4321"),
                    money = getString(R.string.card_value, 100.5),
                    type = MyData.CardType.BLUE
                )
            )
            add(
                Card(
                    cardNumber = getString(R.string.card_description, "9876"),
                    money = getString(R.string.card_value, 0.5),
                    type = MyData.CardType.GOLD
                )
            )
            add(Card(cardNumber = "", money = "", type = MyData.CardType.PLATINUM))
            add(
                Card(
                    cardNumber = getString(R.string.card_description, "2314"),
                    money = getString(R.string.card_value, 9.5),
                    type = MyData.CardType.BLUE
                )
            )
            add(
                Card(
                    cardNumber = getString(R.string.card_description, "2314"),
                    money = getString(R.string.card_value, 9.5),
                    type = MyData.CardType.BLUE
                )
            )
            add(
                Card(
                    cardNumber = getString(R.string.card_description, "2314"),
                    money = getString(R.string.card_value, 9.5),
                    type = MyData.CardType.BLUE
                )
            )
            add(
                Card(
                    cardNumber = getString(R.string.card_description, "2314"),
                    money = getString(R.string.card_value, 9.5),
                    type = MyData.CardType.BLUE
                )
            )
            add(
                Card(
                    cardNumber = getString(R.string.card_description, "2314"),
                    money = getString(R.string.card_value, 9.5),
                    type = MyData.CardType.BLUE
                )
            )
        }
        presenter.list = myList
    }

    private fun onClicked(type: String) {
        Toast.makeText(this, type, Toast.LENGTH_SHORT).show()
    }
}