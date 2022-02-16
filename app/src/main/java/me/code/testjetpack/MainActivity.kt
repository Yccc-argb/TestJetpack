package me.code.testjetpack

import android.util.Log
import okhttp3.*
import java.io.IOException

class MainActivity : BaseActivity() {
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun initView() {

//        val okHttpClient = OkHttpClient()
//        val request = Request.Builder().headers()
//        val newCall = okHttpClient.newCall(request)
//        newCall.enqueue(object : Callback{
//            override fun onFailure(call: Call, e: IOException) {
//
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                response.body()?.string()
//            }
//        })
        Log.i("smallWidth","${resources.configuration.smallestScreenWidthDp}")
    }

}