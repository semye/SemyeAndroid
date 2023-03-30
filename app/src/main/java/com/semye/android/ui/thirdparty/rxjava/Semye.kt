package com.semye.android.ui.thirdparty.rxjava

import io.reactivex.Observable
import io.reactivex.ObservableSource
import java.util.*
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit

/**
 * Created by yesheng on 2019-09-11
 */
object Semye {
    /**
     * 只接收第一个发送通知的Observable 其他的都舍弃掉
     */
    fun amb(): Observable<String> {
        return Observable.amb(object : Iterable<ObservableSource<String>> {
            override fun iterator(): Iterator<ObservableSource<String>> {
                val observable1 = Observable.just("1", "2", "3").delay(3, TimeUnit.SECONDS)
                val observable2 = Observable.just("4", "5", "6").delay(2, TimeUnit.SECONDS)
                val observable3 = Observable.just("7", "8", "9").delay(4, TimeUnit.SECONDS)
                val list: MutableList<ObservableSource<String>> = ArrayList()
                list.add(observable1)
                list.add(observable2)
                list.add(observable3)
                return list.iterator()
            }
        })
    }

    fun fromFeature(): Observable<String> {
        return Observable.fromFuture(FutureTask { "hello" })
    }
}