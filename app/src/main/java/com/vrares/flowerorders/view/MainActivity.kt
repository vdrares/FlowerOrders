package com.vrares.flowerorders.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vrares.flowerorders.R
import com.vrares.flowerorders.viewmodel.OrdersViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewModel = OrdersViewModel()
        viewModel.getOrders()
    }
}
