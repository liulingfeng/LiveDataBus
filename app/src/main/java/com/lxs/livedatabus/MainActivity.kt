package com.lxs.livedatabus

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LiveDataBus.get().with(SecondActivity.CHANGE_COUNT, String::class.java)?.observe(this, Observer {
            tv_count.text = "消息数量$it"
        })

        btn_jump.setOnClickListener {
            SecondActivity.open(this)
        }
    }
}
