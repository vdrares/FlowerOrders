package com.vrares.flowerorders.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.vrares.flowerorders.R
import com.vrares.flowerorders.databinding.ActivityOrdersBinding
import com.vrares.flowerorders.model.models.Order
import com.vrares.flowerorders.viewmodel.OrdersViewModel

class OrdersActivity : AppCompatActivity(), OnOrderClickListener {

    companion object {
        const val EXTRA_ORDER = "extraOrder"
    }

    private var viewModel: OrdersViewModel = OrdersViewModel()
    private lateinit var binding: ActivityOrdersBinding
    private val ordersList: MutableList<Order> = mutableListOf()
    private val adapter = OrdersAdapter(ordersList, this)

    private val ordersListObserver = Observer<List<Order>> { orders: List<Order>? ->
        run {
            with(ordersList) {
                clear()
                orders?.toMutableList()?.let { addAll(it) }
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_orders)
        binding.viewModel = viewModel
        viewModel.getOrders().observe(this, ordersListObserver)
        binding.ordersRecyclerview.adapter = adapter
    }

    override fun onOrderClicked(order: Order) {
        val intent = Intent(this, OrderDetailsActivity::class.java)
        intent.putExtra(EXTRA_ORDER, order)
        startActivity(intent)

    }

}
