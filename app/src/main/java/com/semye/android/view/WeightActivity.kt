package com.semye.android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 *  Created by yesheng on 2020/11/24
 *  view 布局完成后的剩余空间按权重分配
 *  当设置宽度为match_parent时,空间宽度计算公式
 *  1+(1-n)*(控件权重/权重和
 *  n为控件个数
 *
 *  例如权重比为 1:2:3 所有控件宽为parent
 *  则权重1的宽为  1+(-2)*(1/6)= 2/3
 *
 */
class WeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)
    }
}