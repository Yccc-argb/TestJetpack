package me.code.testjetpack.proxy

import java.lang.reflect.Method

class LoadService(private val method : Method) {


    fun invoke(args: Array<out Any>?){
        println("----执行LoadService.invoke----")
    }
}