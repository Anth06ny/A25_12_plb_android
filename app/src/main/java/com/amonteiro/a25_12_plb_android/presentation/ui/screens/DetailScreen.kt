package com.amonteiro.a25_12_plb_android.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.amonteiro.a25_12_plb_android.data.remote.DescriptionBean
import com.amonteiro.a25_12_plb_android.data.remote.TempBean
import com.amonteiro.a25_12_plb_android.data.remote.WeatherBean
import com.amonteiro.a25_12_plb_android.data.remote.WindBean
import com.amonteiro.a25_12_plb_android.presentation.ui.theme.A25_12_plb_androidTheme

@Preview(showBackground = true, showSystemUi = true)
@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
            or android.content.res.Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun DetailScreenPreview() {
    A25_12_plb_androidTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DetailScreen(
                modifier = Modifier.padding(innerPadding),
                //jeu de donnée pour la Preview
                data = WeatherBean(
                    id = 2,
                    name = "Toulouse",
                    main = TempBean(temp = 22.3),
                    weather = listOf(
                        DescriptionBean(description = "partiellement nuageux", icon = "https://picsum.photos/201")
                    ),
                    wind = WindBean(speed = 3.2)
                )
            )
        }
    }
}

@Composable //id du WeatherBean à afficher
fun DetailScreen(
    modifier: Modifier = Modifier,
    data: WeatherBean,
    onBackClick: () -> Unit = {}
) {


    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = data.name,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxWidth()
        )

        AsyncImage(
            model = data.weather.firstOrNull()?.icon ?: "",
            contentDescription = data.weather.firstOrNull()?.description ?: "",
            contentScale = ContentScale.FillWidth,
            //Pour toto.png. Si besoin de choisir l'import pour la classe R, c'est celle de votre package
            //Image d'échec de chargement qui sera utilisé par la preview
            //error = painterResource(R.drawable.toto),
            //Image d'attente.
            //placeholder = painterResource(R.drawable.toto),
            onError = { println(it) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Text(
            text = data.getResume(),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(16.dp))

        Button(
            onClick = onBackClick,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
            modifier = Modifier
                .padding(8.dp)
                .align(CenterHorizontally)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Retour")
        }
    }

}