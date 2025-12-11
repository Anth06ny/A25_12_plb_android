package com.amonteiro.a25_12_plb_android.exo


fun main() {
    var t1 = ThermometerBean(min=-20, max= 50, value =0)
    println("Température de ${t1.value}") // 0

    //Cas qui marche
    t1.value = 10
    println("Température de ${t1.value}") // 10 attendu

    //Borne minimum
    t1.value = -30
    println("Température de ${t1.value}") // -20 attendu

    //Borne maximum
    t1.value = 100
    println("Température de ${t1.value}") // 50 attendu

    //Pour les plus rapides : Cas de départ
    t1 = ThermometerBean(min=-20, max= 50, value =-100)
    println("Température de ${t1.value}") // -20 attendu

    val t = ThermometerBean.getCelsiusThermometer()
}

data class CarBean(var name : String, var old : Int)

class ThermometerBean(val min: Int, val max: Int, value: Int) {
    var value = value.coerceIn(min, max)
        set(newValue) {
            field = if (newValue < min) min else if (newValue > max) max else newValue
        }

    companion object {
        fun getCelsiusThermometer() = ThermometerBean(-30, 50, 0)
        fun getFahrenheitThermometer() = ThermometerBean(20, 120, 32)
    }

    override fun toString(): String {
        return "ThermometerBean(min=$min, max=$max, value=$value)"
    }


}

class RandomName {
    private val list = arrayListOf("Toto", "Tata", "Bob")
    private var old = ""

    fun add(name: String) {
        if (name.isNotBlank() && name !in list) {
            list += name
        }
    }

    fun addV2(name: String) = if (name.isNotBlank() && name !in list)  list.add(name) else false

    fun addAll(vararg nameList : String) {
        for(name  in nameList){
            add(name)
        }
    }

    fun addAllV2(vararg nameList : String) =        nameList.forEach{ add(it)}

    //version simple
    fun next() = list.random()

    //version pas 2 identique de suite
    fun nextDiff(): String {
        var send = next()
        while (send == old) {
            send = next()
        }
        old = send
        return send
    }

    //Idem sur une ligne
    //Also retourne l'objet sur lequel il est appelé
    fun nextDiffV2() = list.filter { it != old }.random().also { old = it }

    //Retourne 2 noms différents
    fun next2() = Pair(nextDiffV2(), nextDiffV2())
}
