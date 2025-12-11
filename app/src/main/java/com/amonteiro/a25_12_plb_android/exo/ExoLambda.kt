package com.amonteiro.a25_12_plb_android.exo

class MyLiveData<T>(value: T) {

    var value = value
        set(newValue) {
            field = newValue
            action(newValue)
        }

    var action: (T) -> Unit = {}


}


fun main() {

    var toto = MyLiveData(CarBean("hello" , 0))

    toto.value = CarBean("hello" , 10)

    toto.action = {
        println(it)
    }
    toto.value = CarBean("hello" , 20)

    toto.value =CarBean(toto.value.name, toto.value.old +10)

    toto.value = toto.value.copy(old = toto.value.old +10)
}