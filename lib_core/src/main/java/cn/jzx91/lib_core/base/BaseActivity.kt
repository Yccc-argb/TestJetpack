package cn.jzx91.lib_core.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType
import kotlin.system.measureTimeMillis

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {



    protected lateinit var binding : T

    protected val TAG = this::class.java.simpleName

//    protected var baseBinding : ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val measureTimeMillis = measureTimeMillis {
            try {
                val type = this.javaClass.genericSuperclass
                if (type is ParameterizedType) {
                    //对应生成在build/generated/data_binding_base_class_source_out目录下
                    //得到xxxBinding字节对象
                    val clazz = type.actualTypeArguments[0] as Class<T>
                    //获取xxxBinding的inflate方法
                    val method = clazz.getMethod("inflate", LayoutInflater::class.java)
                    //执行xxxBinding.inflate方法获取xxxBing对象实例
                    binding = method.invoke(null, getLayoutInflater()) as T
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