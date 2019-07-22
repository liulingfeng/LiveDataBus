package com.lxs.livedatabus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

/**
 * @author liuxiaoshuai
 * @date 2019-07-22
 * @desc
 * @email liulingfeng@mistong.com
 */
class SecondActivity : AppCompatActivity() {
    companion object {
        val CHANGE_COUNT = "${SecondActivity::class.java.name}change_name"

        fun open(context: Context) {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btn_jump.setOnClickListener {
            LiveDataBus.get().with(CHANGE_COUNT, String::class.java)?.value = "24"
        }
    }
}