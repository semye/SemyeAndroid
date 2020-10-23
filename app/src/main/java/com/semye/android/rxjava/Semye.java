package com.semye.android.rxjava;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Created by yesheng on 2019-09-11
 */
public class Semye {


    /**
     * 只接收第一个发送通知的Observable 其他的都舍弃掉
     */
    public static Observable<String> amb() {
        return Observable.amb(new Iterable<ObservableSource<String>>() {
            @NonNull
            @Override
            public Iterator<ObservableSource<String>> iterator() {
                Observable<String> observable1 = Observable.just("1", "2", "3").delay(3, TimeUnit.SECONDS);
                Observable<String> observable2 = Observable.just("4", "5", "6").delay(2, TimeUnit.SECONDS);
                Observable<String> observable3 = Observable.just("7", "8", "9").delay(4, TimeUnit.SECONDS);
                List<ObservableSource<String>> list = new ArrayList<>();
                list.add(observable1);
                list.add(observable2);
                list.add(observable3);
                return list.iterator();
            }
        });
    }


    public static Observable<String> fromFeature() {
        return Observable.fromFuture(new FutureTask<>(new Callable<String>() {
            @NonNull
            @Override
            public String call() {
                return "hello";
            }
        }));
    }

}
