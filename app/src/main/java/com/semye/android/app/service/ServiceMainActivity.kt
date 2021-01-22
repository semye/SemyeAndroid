package com.semye.android.app.service

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R
import com.semye.android.app.service.job.SemyeJobService

/**
 *  Created by yesheng on 2020/11/12
 *  @see android.app.Service
 *  @see android.app.IntentService
 *  @see android.app.job.JobService
 */
class ServiceMainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mStartIntentServiceButton: Button
    private lateinit var mStartServiceButton: Button
    private lateinit var mStartBindServiceButton: Button
    private lateinit var mStopBindServiceButton: Button
    private lateinit var mStartJobServiceButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        mStartIntentServiceButton = findViewById(R.id.btn1)
        mStartServiceButton = findViewById(R.id.btn2)
        mStartBindServiceButton = findViewById(R.id.btn3)
        mStopBindServiceButton = findViewById(R.id.btn4)
        mStartJobServiceButton = findViewById(R.id.btn5)
        mStartIntentServiceButton.setOnClickListener(this)
        mStartServiceButton.setOnClickListener(this)
        mStartBindServiceButton.setOnClickListener(this)
        mStopBindServiceButton.setOnClickListener(this)
        mStartJobServiceButton.setOnClickListener(this)
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
            R.id.btn3 -> {
                kotlin.runCatching {
                    val intent = Intent(this, BindService::class.java)
                    bindService(intent, CustomServiceConnection, Context.BIND_AUTO_CREATE)
                }
            }
            R.id.btn4 -> {
                kotlin.runCatching {
                    unbindService(CustomServiceConnection)
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
        }
    }
}