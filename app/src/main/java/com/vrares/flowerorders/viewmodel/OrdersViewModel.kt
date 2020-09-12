package com.vrares.flowerorders.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vrares.flowerorders.model.OrdersRepository
import com.vrares.flowerorders.model.models.Order
import io.reactivex.disposables.CompositeDisposable

class OrdersViewModel {

    private var myCompositeDisposable: CompositeDisposable? = null
    private val ordersRepository = OrdersRepository()

    private val ordersList = MutableLiveData<List<Order>>()

    init {
        myCompositeDisposable = CompositeDisposable()
        myCompositeDisposable?.add(ordersRepository.getOrders(callback = object : Callback {
            override fun onSuccess(list: List<Order>) {
                ordersList.value = list
            }

            override fun unError(throwable: Throwable) {

            }

        }))
    }

    fun getOrders(): MutableLiveData<List<Order>> {
        return ordersList
    }

}