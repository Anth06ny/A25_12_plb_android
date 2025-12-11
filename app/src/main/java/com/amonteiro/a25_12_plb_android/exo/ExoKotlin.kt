package com.amonteiro.a25_12_plb_android.exo

import android.R.attr.text
import android.util.Log.v
import kotlin.concurrent.thread
import kotlin.random.Random

var v2: String? = "coucou"

fun main() {

    var user = UserBean("bla", "pass")
    user.age = 5
    var user3 = UserBean("bla", "pass")
    var user2 = user

    println(user === user2)
    println(user === user3)



}

data class UserBean(var email: String = "", var password: String? = null) {
    var age: Int =0


}

fun boulangerie(nbCroi:Int = 0, nbBag:Int =0, nbSand:Int =0 ) =
    nbCroi * PRICE_CROISSANT + nbBag * PRICE_BAGUETTE + nbSand * PRICE_SANDWITCH