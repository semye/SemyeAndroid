package com.semye.android.ui.jetpack.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.semye.android.R
import com.semye.android.ui.jetpack.recyclerview.adapter.MainAdapter
import kotlinx.coroutines.launch

/**
 * Created by yesheng on 2019-09-12
 */
class RecyclerViewActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: MainAdapter? = null
    private var mOrientation: Int = RecyclerView.VERTICAL

    companion object {
        const val ORIENTATION = "orientation"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)
        mRecyclerView = findViewById(R.id.recyclerview)
        mOrientation = intent.getIntExtra(ORIENTATION, RecyclerView.VERTICAL)

        lifecycleScope.launch {

        }

        //        RecyclerViewModel recyclerViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(RecyclerViewModel.class);
        //        recyclerViewModel.setLiveData();
        //        recyclerViewModel.liveData.observe(this, new Observer<List<String>>() {
        //            @Override
        //            public void onChanged(List<String> strings) {
        //                if (mAdapter != null) {
        //                    mAdapter.setData(strings);
        //                }
        //            }
        //        });
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(this, mOrientation, false)
        mRecyclerView?.layoutManager = linearLayoutManager
        mRecyclerView?.addItemDecoration(
            DividerItemDecoration(
                this,
                mOrientation
            )
        )
        mAdapter = MainAdapter()
        mRecyclerView?.adapter = mAdapter


        mRecyclerView?.setRecyclerListener {

        }
    }
}