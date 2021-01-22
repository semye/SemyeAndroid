package com.semye.android.thirdparty.rxjava

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.CompletableObserver
import io.reactivex.CompletableOnSubscribe
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import io.reactivex.FlowableSubscriber
import io.reactivex.Maybe
import io.reactivex.MaybeEmitter
import io.reactivex.MaybeObserver
import io.reactivex.MaybeOnSubscribe
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleObserver
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription

/**
 * reactive x 响应式编程
 *
 *
 * facade
 *
 * @see io.reactivex.Single
 *
 * @see io.reactivex.Observable
 *
 * @see io.reactivex.Maybe
 *
 * @see io.reactivex.Flowable
 *
 * @see io.reactivex.Completable
 *
 *
 * 操作符
 */
class RxjavaMainActivity : AppCompatActivity(), View.OnClickListener {
    private var mBtn1: Button? = null
    private var mBtn2: Button? = null
    private var mBtn3: Button? = null
    private var mBtn4: Button? = null
    private var mBtn5: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)
        mBtn1 = findViewById(R.id.btn1)
        mBtn2 = findViewById(R.id.btn2)
        mBtn3 = findViewById(R.id.btn3)
        mBtn4 = findViewById(R.id.btn4)
        mBtn5 = findViewById(R.id.btn5)
        mBtn1?.setOnClickListener(this)
        mBtn2?.setOnClickListener(this)
        mBtn3?.setOnClickListener(this)
        mBtn4?.setOnClickListener(this)
        mBtn5?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.btn1 -> {
                val disposable = Observable.create(object : ObservableOnSubscribe<String> {
                    override fun subscribe(emitter: ObservableEmitter<String>) {
                        println("subscribe")
                        emitter.onNext("hello")
                        emitter.onNext("world")
                        emitter.onComplete()
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()) //订阅在线程
                    .observeOn(Schedulers.io()) //观察在线程
                    .subscribe(object : Observer<String> {
                        override fun onSubscribe(d: Disposable) {
                            println("onSubscribe:" + Thread.currentThread())
                        }

                        override fun onNext(t: String) {
                            println("onNext:" + t)
                            println("onNext:" + Thread.currentThread())
                        }

                        override fun onError(e: Throwable) {
                            println("onError:" + Thread.currentThread())
                        }

                        override fun onComplete() {
                            println("onError" + Thread.currentThread())
                        }

                    })
            }
            R.id.btn2 -> {
                val disposable = Flowable.create(object : FlowableOnSubscribe<String> {
                    override fun subscribe(emitter: FlowableEmitter<String>) {
                        emitter.onNext("yesheng")
                        emitter.onComplete()
                    }
                }, BackpressureStrategy.BUFFER)
                    .subscribeOn(AndroidSchedulers.mainThread()) //订阅在线程
                    .observeOn(Schedulers.io()) //观察在线程
                    .subscribe(object : FlowableSubscriber<String> {
                        override fun onSubscribe(s: Subscription) {
                            println("onSubscribe")
                        }

                        override fun onNext(t: String?) {
                            println("onNext:" + t)
                        }

                        override fun onError(t: Throwable?) {
                            println("onError")
                        }

                        override fun onComplete() {
                            println("onComplete")
                        }

                    })
            }
            R.id.btn3 -> {
                Maybe.create(object : MaybeOnSubscribe<String> {
                    override fun subscribe(emitter: MaybeEmitter<String>) {
                        emitter.onSuccess("helllo1")
                        emitter.onSuccess("helllo2")
                        emitter.onComplete()
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()) //订阅在线程
                    .observeOn(Schedulers.io()) //观察在线程
                    .subscribe(object : MaybeObserver<String> {
                        override fun onSubscribe(s: Disposable) {
                            println("onSubscribe")
                        }

                        override fun onComplete() {
                            println("onComplete")
                        }

                        override fun onSuccess(t: String) {
                            println("onComplete:" + t)
                        }

                        override fun onError(e: Throwable) {
                            println("onError")
                        }
                    })
            }
            R.id.btn4 -> {
                Single.create(object : SingleOnSubscribe<String> {
                    override fun subscribe(emitter: SingleEmitter<String>) {
                        emitter.onSuccess("helllo")
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()) //订阅在线程
                    .observeOn(Schedulers.io())//观察在线程
                    .subscribe(object : SingleObserver<String> {
                        override fun onSubscribe(d: Disposable) {
                            println("onSubscribe")
                        }

                        override fun onSuccess(t: String) {
                            println("onSuccess" + t)
                        }

                        override fun onError(e: Throwable) {
                            println("onError")
                        }
                    })
            }
            R.id.btn5 -> {
                Completable.create(object : CompletableOnSubscribe {
                    override fun subscribe(emitter: CompletableEmitter) {
                        emitter.onComplete()
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()) //订阅在线程
                    .observeOn(Schedulers.io())//观察在线程
                    .subscribe(object : CompletableObserver {
                        override fun onSubscribe(d: Disposable) {
                            println("onSubscribe")
                        }

                        override fun onComplete() {
                            println("onComplete")
                        }

                        override fun onError(e: Throwable) {
                            println("onError")
                        }
                    })
            }
        }
    }

    companion object {
        private val TAG = RxjavaMainActivity::class.java.simpleName
    }
}