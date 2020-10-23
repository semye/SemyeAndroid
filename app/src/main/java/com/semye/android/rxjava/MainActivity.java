package com.semye.android.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.semye.android.R;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amb();


    }


    private void fromFeature() {
        Disposable disposable = Semye.fromFeature().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }


    @SuppressLint("CheckResult")
    private void amb() {
        Semye.amb().subscribe(new Consumer<String>() {

            @Override
            public void accept(String s) {
                Log.d(TAG, "accept: " + s);
            }
        });
    }

    private void ambArray() {

        Disposable disposable = Observable.ambArray(new ObservableSource<String>() {
            @Override
            public void subscribe(@NonNull Observer<? super String> observer) {
                observer.onNext("1111");
            }
        }, new ObservableSource<String>() {
            @Override
            public void subscribe(@NonNull Observer<? super String> observer) {
                observer.onNext("22222");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });
    }

    private void fromArray() {
        Observable.fromArray("java", "c++", "c#")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: " + s);
                    }
                }).dispose();
    }


    private void create() {
        getCommonObservable().subscribe(new TestObserver<>());

        getCommonObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        }).dispose();

        getCommonObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }).dispose();


        getCommonObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        }).dispose();


        getCommonObservable().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: " + Thread.currentThread().getName());
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + Thread.currentThread().getName());
                        Log.d(TAG, "onNext: " + s);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + Thread.currentThread().getName());
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: " + Thread.currentThread().getName());
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    /**
     * 间隔  每隔指定周期发射一次 默认在计算线程池中执行
     */
    private void interval() {
        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable<Long> observable = Observable.interval(0, 2000, TimeUnit.MILLISECONDS);
        Disposable disposable = observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG, "onNext: " + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + aLong);
                if (aLong == 5) {
                    compositeDisposable.dispose();
                }
            }
        });
        compositeDisposable.add(disposable);
    }

    /**
     * 重复执行Observable发射
     */
    private void repeat() {
        Disposable disposable = getCommonObservable()
                .repeat(4)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: " + s);
                    }
                });
    }

    private void start() {
        Disposable disposable = getCommonObservable().startWith("fuck....")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: " + s);

                    }
                });
    }

    private void timer() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribe();
    }

    /**
     * 创建一个公共的Observable
     *
     * @return
     */
    private Observable<String> getCommonObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "subscribe: ");
                emitter.onNext("hello1");
                emitter.onNext("hello2");
                emitter.onNext("hello3");
                emitter.onNext("hello4");
                emitter.onNext("hello5");
                emitter.onNext("hello6");
                emitter.onComplete();
            }
        });
    }

    /**
     * 创建一个公共的Observable
     *
     * @return
     */
    private Observable<String> getCommonObservable1() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "subscribe: ");
                emitter.onNext("hello1");
                emitter.onNext("hello2");
                emitter.onNext("hello3");
                emitter.onNext("hello4");
                emitter.onNext("hello6");
                emitter.onComplete();
            }
        });
    }

    /**
     * 返回了一个发射了一个序列的Observable 序列的范围就是start+count
     */
    private void range() {
        Disposable disposable = Observable.range(3, 5).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept: " + integer);
            }
        });
    }


    /**
     * Observable只有订阅时才创建
     */
    private void defer() {
        Disposable disposable = Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                return getCommonObservable();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);

            }
        });
    }


    //=================================变换操作==============================

    /**
     * 缓冲操作  将observable发射的数据缓存起来 等达到一定数量一起发射
     */
    private void buffer() {
        //        Disposable disposable1 = getCommonObservable().buffer(3)
        //                .subscribe(getBufferConsumer("disposable1"));
        //        Disposable disposable2 = getCommonObservable().buffer(3, 1)
        //                .subscribe(getBufferConsumer("disposable2"));
        //        Disposable disposable3 = getCommonObservable().buffer(1000, TimeUnit.MILLISECONDS)
        //                .subscribe(getBufferConsumer("disposable3"));
        Disposable disposable4 = getCommonObservable().buffer(new ObservableSource<String>() {
            @Override
            public void subscribe(Observer<? super String> observer) {

            }
        }).subscribe(getBufferConsumer("disposable4"));
    }

    private Consumer<List<String>> getBufferConsumer(final String tag) {
        return new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                Log.d(TAG, tag + "====>: " + strings);
            }
        };
    }

    /**
     * flatmap merge data
     */
    private void flatMap() {
        Disposable disposable = getCommonObservable().flatMap(new Function<String, ObservableSource<List<String>>>() {
            @NonNull
            @Override
            public ObservableSource<List<String>> apply(final String s) throws Exception {
                return new ObservableSource<List<String>>() {
                    @Override
                    public void subscribe(@NonNull Observer<? super List<String>> observer) {
                        List<String> list = new ArrayList<>();
                        list.add(s);
                        observer.onNext(list);
                        observer.onComplete();
                    }
                };
            }
        }).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                Log.d(TAG, "accept: " + strings);
            }
        });
    }

    /**
     * 为每个发射的数据执行一个函数来进行变换过程
     */
    private void map() {
        Disposable disposable = getCommonObservable()
                .map(new Function<String, ArrayList<String>>() {
                    @NonNull
                    @Override
                    public ArrayList<String> apply(String s) throws Exception {
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(s);
                        return arrayList;
                    }
                }).subscribe(new Consumer<ArrayList<String>>() {
                    @Override
                    public void accept(ArrayList<String> strings) throws Exception {

                    }
                });
    }

    /**
     * 分组操作
     */
    private void groupBy() {
        Disposable disposable = getCommonObservable()
                .groupBy(new Function<String, String>() {
                    @NonNull
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        if (s.contains("1")) {
                            return s;
                        } else {
                            return "2";
                        }
                    }
                }).subscribe(new Consumer<GroupedObservable<String, String>>() {
                    @Override
                    public void accept(@NonNull GroupedObservable<String, String> listStringGroupedObservable) throws Exception {
                        Log.d(TAG, "GroupedObservable: " + listStringGroupedObservable.getKey());
                        Disposable disposable1 = listStringGroupedObservable
                                .buffer(6)
                                .subscribe(new Consumer<List<String>>() {
                                    @Override
                                    public void accept(List<String> strings) throws Exception {
                                        Log.d(TAG, "accept: " + strings);
                                    }
                                });
                    }
                });
    }

    private void scan() {
        Disposable disposable = getCommonObservable().scan(new BiFunction<String, String, String>() {
            @NonNull
            @Override
            public String apply(String s, String s2) throws Exception {
                Log.d(TAG, "s: " + s);
                Log.d(TAG, "s2: " + s2);
                return s + s2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });
    }

    private void window() {
        Disposable disposable = getCommonObservable().window(3)
                .subscribe(new Consumer<Observable<String>>() {
                    @Override
                    public void accept(Observable<String> stringObservable) throws Exception {
                    }
                });
    }

    //========================================过滤操作符=================================================

    private void debounce() {
    }

    /**
     * 发射第一个数据
     */
    private void first() {
        Disposable disposable = getCommonObservable().first("no item")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: " + s);
                    }
                });
    }

    private void last() {
        Disposable disposable = getCommonObservable().last("no item")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: " + s);
                    }
                });
    }

    private void elementAt() {
        Disposable disposable = getCommonObservable().elementAt(3, "no item")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: " + s);
                    }
                });
    }

    private void filter() {
        Disposable disposable = getCommonObservable().filter(new Predicate<String>() {
            @Override
            public boolean test(@NonNull String s) throws Exception {
                if (s.contains("1")) {
                    return true;
                }
                return false;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });
    }


    //=================================================结合操作符=====================================================================


    /**
     * 结合最新的
     */
    private void combineLatest() {
        Disposable disposable = Observable.combineLatest(getCommonObservable(),
                getCommonObservable(),
                new BiFunction<String, String, Boolean>() {
                    @NonNull
                    @Override
                    public Boolean apply(@NonNull String s, @NonNull String s2) throws Exception {
                        return s.contains("1") || s2.contains("1");
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean s) throws Exception {
                if (s) {
                    Log.d(TAG, "accept: =====>fuck");
                }
            }
        });
    }

    /**
     * 合并
     */
    private void merge() {
        Disposable disposable = Observable.merge(getCommonObservable(), getCommonObservable())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "merge accept: " + s);
                    }
                });

    }

    /**
     * 压缩 11对应
     */
    private void zip() {
        Disposable disposable = Observable.zip(getCommonObservable(), getCommonObservable1(), new BiFunction<String, String, String>() {
            @NonNull
            @Override
            public String apply(String s, String s2) throws Exception {
                return s + s2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "zip accept: " + s);
            }
        });

    }

    /**
     * switch
     */
    private void switchOperators() {
        Disposable disposable = getCommonObservable().switchMap(new Function<String, ObservableSource<String>>() {
            @Nullable
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return null;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }


    private void join() {


        Disposable disposable = getCommonObservable().join(getCommonObservable(),
                new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return getCommonObservable();
                    }
                }, new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String tRight) throws Exception {
                        return getCommonObservable();
                    }
                }, new BiFunction<String, String, String>() {
                    @Nullable
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        return null;
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });
    }


    private void flowable() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {

            }
        }, BackpressureStrategy.LATEST)
                .subscribe(new FlowableSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //        tests();
    ////        tefsdf();
    //        hellll();
    //        Button button1 = findViewById(R.id.button1);
    //        button1.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                map(editText.getText().toString());
    //            }
    //        });
    //        Button button2 = findViewById(R.id.button2);
    //        button2.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                flatMap();
    //            }
    //        });
    //        Button just = findViewById(R.id.button3);
    //        just.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                just();
    //            }
    //        });
    //
    //        Flowable.just("hello world").subscribe(new Consumer<String>() {
    //            @Override
    //            public void accept(String s) throws Exception {
    //                System.out.println(s);
    //            }
    //        });
    //
    //
    //        observableTextChange().observeOn(Schedulers.io()).subscribeOn(Schedulers.newThread()).subscribe(new Observer<String>() {
    //            @Override
    //            public void onSubscribe(Disposable d) {
    //
    //            }
    //
    //            @Override
    //            public void onNext(String value) {
    //
    //            }
    //
    //            @Override
    //            public void onError(Throwable e) {
    //
    //            }
    //
    //            @Override
    //            public void onComplete() {
    //
    //            }
    //        });


    public void hellll() {
        String[] strings = new String[]{"hhhhhh", "eeeeeee", "llllllllll"};
        Observable.fromArray(strings)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        Log.d("hah", value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void sss() {

    }

    public void tests() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("fuck");
                e.onNext("you");
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("hah", "subscribe");
                Log.d("hah", d.isDisposed() + "");
            }

            @Override
            public void onNext(String value) {
                Log.d("hah", value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("hah", "complete");
            }
        });
    }


    public void just() {
        Observable.just("just 111", "just 222")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("hah", "just onSubscribe");
                    }

                    @Override
                    public void onNext(String value) {
                        Log.d("hah", value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("hah", "just onComplete");
                    }
                });
    }


    private void just2() {
        Observable.just("1213", "2134567")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: " + s);
                    }
                }).dispose();
    }


    public void wrap() {

        Observable.wrap(new ObservableSource<String>() {

            @Override
            public void subscribe(Observer<? super String> observer) {

            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void tefsdf() {
        Observable.fromFuture(new FutureTask<String>(new Callable<String>() {
            @NonNull
            @Override
            public String call() throws Exception {
                return "hahha";
            }
        })).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d("hah", value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void test(@NonNull Function<String, Integer> func1) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

            }
        }).map(func1);

        //        Observable.create(new Observable.OnSubscribe<String>() {
        //            @Override
        //            public void call(Subscriber<? super String> subscriber) {
        //
        //            }
        //        }).map(func1);
    }

    /**
     * flatmap 操作符的使用方法 结合retrofit使用更好
     * 获取token然后再上传图片或视频 链式执行
     */
    private void flatMap2() {//链式处理
        //        Observable.create(new Observable.OnSubscribe<String>() {
        //            @Override
        //            public void call(Subscriber<? super String> subscriber) {
        //                subscriber.onNext("获取上传图片的token");
        //                subscriber.onCompleted();
        //            }
        //        }).flatMap(new Function<String, Observable<String>>() {
        //            @Override
        //            public Observable<String> call(final String s) {
        //                return Observable.create(new Observable.OnSubscribe<String>() {
        //                    @Override
        //                    public void call(Subscriber<? super String> subscriber) {
        //                        subscriber.onNext(s + "===>" + "上传图片到七牛");
        //                        subscriber.onCompleted();
        //                    }
        //                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        //            }
        //        }).flatMap(new Function<String, Observable<String>>() {
        //            @Override
        //            public Observable<String> call(final String s) {
        //                return Observable.create(new Observable.OnSubscribe<String>() {
        //                    @Override
        //                    public void call(Subscriber<? super String> subscriber) {
        //                        subscriber.onNext(s + "====>" + "获取上传视频的token");
        //                        subscriber.onCompleted();
        //                    }
        //                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        //            }
        //        }).flatMap(new Function<String, Observable<String>>() {
        //            @Override
        //            public Observable<String> call(final String s) {
        //                return Observable.create(new Observable.OnSubscribe<String>() {
        //                    @Override
        //                    public void call(Subscriber<? super String> subscriber) {
        //                        subscriber.onNext(s + "====>" + "上传视频到七牛");
        //                        subscriber.onCompleted();
        //                    }
        //                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        //            }
        //        }).subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Observer<String>() {
        //                    @Override
        //                    public void onCompleted() {
        //
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable e) {
        //                        Toast.makeText(RxAndroidActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        //                    }
        //
        //                    @Override
        //                    public void onNext(String s) {
        //                        Toast.makeText(RxAndroidActivity.this, s, Toast.LENGTH_LONG).show();
        //                    }
        //                });
    }

    private void map(String text) {//map操作符是一对一转
        //        Observable.just(text) // 输入类型 String,输出String
        //                .map(new Func1<String, String>() {
        //
        //                    @Override
        //                    public String call(String s) {
        //                        return "map操作符处理:" + s;
        //                    }
        //                })
        //                .subscribe(new Subscriber<String>() {
        //                    @Override
        //                    public void onCompleted() {
        //
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable e) {
        //                        Toast.makeText(RxAndroidActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        //                    }
        //
        //                    @Override
        //                    public void onNext(String s) {
        //                        Toast.makeText(RxAndroidActivity.this, s, Toast.LENGTH_LONG).show();
        //                    }
        //                });
    }

    private void map2(String text) {
        //        Observable.just(text) // 输入类型 String,输出int
        //                .map(new Func1<String, Integer>() {
        //
        //                    @Override
        //                    public Integer call(String s) {
        //                        return 1;
        //                    }
        //                })
        //                .subscribe(new Subscriber<Integer>() {
        //                    @Override
        //                    public void onCompleted() {
        //
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable e) {
        //                        Toast.makeText(RxAndroidActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        //                    }
        //
        //                    @Override
        //                    public void onNext(Integer s) {
        //                        Toast.makeText(RxAndroidActivity.this, s, Toast.LENGTH_LONG).show();
        //                    }
        //                });
    }


    private Observable<String> observableTextChange() {
        return Observable.fromFuture(new Future<String>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Nullable
            @Override
            public String get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Nullable
            @Override
            public String get(long timeout, @NonNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        });
    }
}
