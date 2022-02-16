package me.code.testjetpack.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class ProxyUtil {

    companion object {
        @JvmStatic
        val  instance by lazy { ProxyUtil() }
    }

    //获取代理对象实例
    fun getInstance(any: Any) : Any{
        val myProxy = MyProxy(any)

        /**
         * any                         被代理对象
         * any.javaClass.classLoader   被代理对象的类加载器
         * any.javaClass.interfaces    被代理对象的接口
         * 通过反射将被代理类的类加载器以及接口 与 代理对象关联起来
         * myProxy                     实现InvocationHandler接口类
         */
        val classLoader = any::class.java.classLoader
        val interfaces = any::class.java.interfaces
        return Proxy.newProxyInstance(classLoader,interfaces,myProxy)
    }


//    fun <T> getInstance(service: Class<T>): T {
//
//        return Proxy.newProxyInstance(
//            service.classLoader,
//            service.interfaces,
//            object : InvocationHandler {
//                override fun invoke(any: Any?, method: Method, args: Array<out Any>?): Any {
//
////                    if (method?.declaringClass == Any::class.java) {
////                        return method.invoke(this, args)
////                    }
//                    return LoadService(method).invoke(args)
//
//                }
//            }) as T
//    }

    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
            service.classLoader,
            arrayOf(service)
        ) { proxy, method, args ->
            return@newProxyInstance LoadService(method).invoke(args ?: arrayOfNulls<Any>(0))
        } as T
    }
}