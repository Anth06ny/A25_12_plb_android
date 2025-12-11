package com.amonteiro.a25_12_plb_android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amonteiro.a25_12_plb_android.data.remote.DescriptionBean
import com.amonteiro.a25_12_plb_android.data.remote.KtorWeatherAPI
import com.amonteiro.a25_12_plb_android.data.remote.TempBean
import com.amonteiro.a25_12_plb_android.data.remote.WeatherBean
import com.amonteiro.a25_12_plb_android.data.remote.WindBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

suspend fun main() {
    val viewModel = MainViewModel()
    viewModel.loadWeathers("")
    //viewModel.loadWeathers("Paris")

    while (viewModel.runInProgress.value) {
        delay(500)  //attente
    }
    //Affichage de la liste et du message d'erreur
    println("List : ${viewModel.dataList.value}")
    println("ErrorMessage : ${viewModel.errorMessage.value}")

    KtorWeatherAPI.close()  //Pour que le programme s'arrête, inutile sur Android
}

class MainViewModel : ViewModel() {
    //MutableStateFlow est une donnée observable
    val dataList = MutableStateFlow(emptyList<WeatherBean>())
    val runInProgress = MutableStateFlow(false)
    val errorMessage = MutableStateFlow("")

    init {
        println("Instanciation de MainViewModel")
        loadFakeData()
    }

    fun loadWeathers(cityName: String) {
        runInProgress.value = true
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            try {
                dataList.value = KtorWeatherAPI.loadWeathers(cityName)
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage.value = e.message ?: "Une erreur est survenue"
            }
            finally {
                runInProgress.value = false
            }
        }

    }

    fun loadFakeData(runInProgress :Boolean = false, errorMessage:String = "" ) {
        this.runInProgress.value = runInProgress
        this.errorMessage.value = errorMessage
        dataList.value = listOf(
            WeatherBean(
                id = 1,
                name = "Paris",
                main = TempBean(temp = 18.5),
                weather = listOf(
                    DescriptionBean(description = "ciel dégagé", icon = "https://picsum.photos/200")
                ),
                wind = WindBean(speed = 5.0)
            ),
            WeatherBean(
                id = 2,
                name = "Toulouse",
                main = TempBean(temp = 22.3),
                weather = listOf(
                    DescriptionBean(description = "partiellement nuageux", icon = "https://picsum.photos/201")
                ),
                wind = WindBean(speed = 3.2)
            ),
            WeatherBean(
                id = 3,
                name = "Toulon",
                main = TempBean(temp = 25.1),
                weather = listOf(
                    DescriptionBean(description = "ensoleillé", icon = "https://picsum.photos/202")
                ),
                wind = WindBean(speed = 6.7)
            ),
            WeatherBean(
                id = 4,
                name = "Lyon",
                main = TempBean(temp = 19.8),
                weather = listOf(
                    DescriptionBean(description = "pluie légère", icon = "https://picsum.photos/203")
                ),
                wind = WindBean(speed = 4.5)
            )
        ).shuffled() //shuffled() pour avoir un ordre différent à chaque appel
    }
}