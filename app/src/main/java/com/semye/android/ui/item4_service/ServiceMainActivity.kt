package com.semye.android.ui.item4_service

import android.app.ActivityManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.IMyAidlInterface
import com.semye.android.R
import com.semye.android.SERVICE_TAG
import com.semye.android.ui.item4_service.job.SemyeJobService

/**
 *  Created by yesheng on 2020/11/12
 *  @see android.app.Service
 *  @see android.app.IntentService
 *  @see android.app.job.JobService
 */
class ServiceMainActivity : AppCompatActivity(), View.OnClickListener, ServiceConnection {

    private lateinit var mStartIntentServiceButton: Button
    private lateinit var mStartServiceButton: Button
    private lateinit var mStartBindServiceButton: Button
    private lateinit var mStopBindServiceButton: Button
    private lateinit var mStartJobServiceButton: Button
    private lateinit var mSearchButton: Button
    private lateinit var mStartServiceButton2: Button
    private lateinit var mSearchButton2: Button
    private var iMyAidlInterface: IMyAidlInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        mStartIntentServiceButton = findViewById(R.id.btn1)
        mStartServiceButton = findViewById(R.id.btn2)
        mStartServiceButton2 = findViewById(R.id.btn22)
        mStartBindServiceButton = findViewById(R.id.btn3)
        mStopBindServiceButton = findViewById(R.id.btn4)
        mStartJobServiceButton = findViewById(R.id.btn5)
        mSearchButton = findViewById(R.id.btn6)
        mSearchButton2 = findViewById(R.id.btn61)
        mStartIntentServiceButton.setOnClickListener(this)
        mStartServiceButton.setOnClickListener(this)
        mStartBindServiceButton.setOnClickListener(this)
        mStopBindServiceButton.setOnClickListener(this)
        mStartJobServiceButton.setOnClickListener(this)
        mSearchButton.setOnClickListener(this)
        mStartServiceButton2.setOnClickListener(this)
        mSearchButton2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                val intent = Intent(this, SemyeService::class.java)
                startService(intent)
            }
            R.id.btn2 -> {
                val intent = Intent(this, NormalService::class.java)
                startService(intent)
            }
            R.id.btn22 -> {
                val intent = Intent(this, NormalService::class.java)
                stopService(intent)
            }
            R.id.btn3 -> {
                kotlin.runCatching {
                    val intent = Intent(this, BindService::class.java)
                    bindService(intent, this, Context.BIND_AUTO_CREATE)
                }
            }
            R.id.btn4 -> {
                kotlin.runCatching {
                    unbindService(this)
                }
            }
            R.id.btn5 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val jobScheduler: JobScheduler =
                        getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
                    val componentName = ComponentName(this, SemyeJobService::class.java)
                    val jobinfo = JobInfo.Builder(100001, componentName)
                        .setPeriodic(
                            15 * 60 * 1000L // 15 minutes
                        )
                        .build()
                    jobScheduler.schedule(jobinfo)
                }
            }
            R.id.btn6 -> {
                val acs = getSystemService(ACTIVITY_SERVICE) as ActivityManager
                acs.getRunningServices(10).forEach() {
                    Log.e("yesheng", it.service.className)
                }
            }
            R.id.btn61 -> {
                iMyAidlInterface?.basicTypes("蜡笔小新")
            }
        }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.d(SERVICE_TAG, "onServiceConnected:")
        iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
    }

    /**
     * service正常关闭时不会调用
     */
    override fun onServiceDisconnected(name: ComponentName?) {
        iMyAidlInterface = null
        Log.d(SERVICE_TAG, "onServiceDisconnected: ")
    }
}