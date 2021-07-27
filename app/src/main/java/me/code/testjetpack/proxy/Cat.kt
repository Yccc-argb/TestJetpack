package me.code.testjetpack.proxy

class Cat : Animal{



    override fun animalVoice() {
        println("~~~喵喵喵~~~")
    }

    override fun animalEat() {
        println("~~~猫粮~~~")
    }
}