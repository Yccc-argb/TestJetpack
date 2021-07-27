package me.code.testjetpack.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class MyProxy : InvocationHandler {


    var original : Any ?=null

    override fun invoke(p0: Any, p1: Method, p2: Array<out Any>): Any {

        println("--->方法执行开始<---")

        val invoke = p1.invoke(original, p0)

        println("--->方法执行结束<---")

        return invoke


    }
}