package me.code.testjetpack

import android.app.AppComponentFactory
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.Appendable

abstract class BaseActivity : AppCompatActivity() {


    abstract val layoutResId : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
    }


    override fun onStart() {
        super.onStart()
    }



    abstract fun initView()
}