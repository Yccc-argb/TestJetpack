package me.code.testjetpack.proxy

class ProxyCat(private val cat: Cat) : Animal {



    override fun animalVoice() {
        println("--->animalVoice Start<---")
        cat.animalVoice()
        println("--->animalVoice End<---")
    }

    override fun animalEat() {
        println("--->animalEat Start<---")
        cat.animalEat()
        println("--->animalEat End<---")
    }
}