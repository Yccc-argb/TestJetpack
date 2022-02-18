package me.code.testjetpack

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import me.code.testjetpack.databinding.ActivityBaseBinding
import java.lang.reflect.ParameterizedType
import kotlin.system.measureTimeMillis

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {



    protected lateinit var binding : T

//    protected var baseBinding : ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val measureTimeMillis = measureTimeMillis {
            try {
                val type = this.javaClass.genericSuperclass
                if (type is ParameterizedType) {
                    val clazz = type.actualTypeArguments[0] as Class<T>
                    val method = clazz.getMethod("inflate", LayoutInflater::class.java)
                    //反射执行xxxbinding.inflate方法
                    binding = method.invoke(null, layoutInflater) as T
                }

                setContentView(binding.root)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        println("---耗时->$measureTimeMillis")
        initView()
    }


    override fun onStart() {
        super.onStart()
    }


    abstract fun initView()
}