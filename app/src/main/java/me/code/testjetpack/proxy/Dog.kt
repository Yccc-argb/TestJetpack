package me.code.testjetpack.proxy

class Dog : Animal{



    override fun animalVoice() {
        println("~~~汪汪汪~~~")
    }

    override fun animalEat() {
        println("~~~狗粮~~~")
    }
}