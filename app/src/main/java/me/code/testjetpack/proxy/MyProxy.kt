package me.code.testjetpack.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class MyProxy(private val original : Any?) : InvocationHandler {


    override fun invoke(p0: Any, p1: Method, p2: Array<out Any>?): Any? {

        println("--->方法执行开始<---")

        val invoke = p1.invoke(original, *(p2 ?: arrayOfNulls<Any>(0)))


        println("--->方法执行结束<---")


        return invoke


    }
}