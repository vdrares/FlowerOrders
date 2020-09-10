package com.vrares.flowerorders.viewmodel

import com.google.gson.GsonBuilder
import com.vrares.flowerorders.model.models.Order
import com.vrares.flowerorders.model.networking.OrdersService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class OrdersViewModel {

    private var myCompositeDisposable: CompositeDisposable? = null

    init {
        myCompositeDisposable = CompositeDisposable()
    }

    fun getOrders() {
        val requestInterface = Retrofit.Builder()
            .baseUrl("http://demo1875631.mockable.io")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(OrdersService::class.java)

        myCompositeDisposable?.add(
            requestInterface.getOrders()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result -> handleResponse(result) },
                    { throwable -> onError(throwable) })
        )
    }

    private fun handleResponse(list: List<Order>) {
        println("VULTURU" + list)
    }

    fun onError(throwable: Throwable) {
        throwable.stackTrace
    }
}