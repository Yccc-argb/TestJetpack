package me.code.testjetpack.proxy

import java.lang.reflect.Proxy

object ProxyUtil {


    //获取代理对象实例
    fun getInstance(any: Any) : Any{
        val myProxy = MyProxy()
        myProxy.original = any

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

}