package me.code.testjetpack.proxy

interface Animal {

    fun animalVoice()

    fun animalEat()



    //等于Java中default修饰方法,实现类可以不用实现该方法
    fun animalName() {
        println("------Name-----")
    }



}