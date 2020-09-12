package com.vrares.flowerorders.viewmodel

import com.vrares.flowerorders.model.models.Order

interface Callback {

    fun onSuccess(list: List<Order>)
    fun unError(throwable: Throwable)

}