package cn.jzx91.lib_core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<T : ViewBinding> : Fragment(){

    protected lateinit var binding: T

    protected val  TAG = this::class.simpleName


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType){
            val clazz = type.actualTypeArguments[0] as Class<T>
            val method = clazz.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
            binding = method.invoke(null,inflater, container, false) as T


        }


        return binding.root
    }

}