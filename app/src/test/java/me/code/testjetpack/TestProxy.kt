package me.code.testjetpack

import kotlinx.coroutines.processNextEventInCurrentThread
import me.code.testjetpack.proxy.Animal
import me.code.testjetpack.proxy.Cat
import me.code.testjetpack.proxy.ProxyCat
import me.code.testjetpack.proxy.ProxyUtil
import org.junit.Test

class TestProxy {

    @Test
    fun testStaticProxy() {
        val cat = Cat()
        val proxyCat = ProxyCat(cat)

        proxyCat.animalVoice()
        proxyCat.animalEat()
    }


    @Test
    fun testDynamicProxy(){
        val instance = ProxyUtil.getInstance(Cat())
        val cat = instance as Animal
        cat.animalVoice()
        cat.animalEat()
    }

}