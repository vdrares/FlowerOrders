package com.vrares.flowerorders.model

import com.google.gson.GsonBuilder
import com.vrares.flowerorders.model.networking.OrdersService
import com.vrares.flowerorders.viewmodel.Callback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class OrdersRepository {

    private val requestInterface: OrdersService = Retrofit.Builder()
        .baseUrl("http://demo1875631.mockable.io")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(OrdersService::class.java)

    fun getOrders(callback: Callback): Disposable {
        return requestInterface.getOrders()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result -> callback.onSuccess(result) },
                { throwable -> callback.unError(throwable) }
            )
    }

}