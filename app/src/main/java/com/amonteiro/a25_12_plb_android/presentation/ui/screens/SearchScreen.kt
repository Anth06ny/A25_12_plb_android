package com.amonteiro.a25_12_plb_android.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.amonteiro.a25_12_plb_android.R
import com.amonteiro.a25_12_plb_android.data.remote.WeatherBean
import com.amonteiro.a25_12_plb_android.presentation.ui.theme.A25_12_plb_androidTheme
import com.amonteiro.a25_12_plb_android.presentation.viewmodel.MainViewModel


@Preview(showBackground = true, showSystemUi = true)
@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES or android.content.res.Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SearchScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    A25_12_plb_androidTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SearchScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier,
                 mainViewModel: MainViewModel = MainViewModel()) {

    Column(modifier = modifier.fillMaxSize()) {

        val list = mainViewModel.dataList.collectAsStateWithLifecycle().value

        list.forEach {
            PictureRowItem(data = it, modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainerHigh))
        }
    }
}

@Composable //Composable affichant 1 élément
fun PictureRowItem(modifier: Modifier = Modifier, data: WeatherBean) {
    Row(modifier = modifier.fillMaxWidth().padding(4.dp)) {

        //Permission Internet nécessaire
        AsyncImage(
            model = data.weather.firstOrNull()?.icon,
            //Pour aller le chercher dans string.xml R de votre package com.nom.projet
            //contentDescription = getString(R.string.picture_of_cat),
            //En dur
            contentDescription = "une photo de chat",
            contentScale = ContentScale.FillWidth,

            //Pour toto.png. Si besoin de choisir l'import pour la classe R, c'est celle de votre package
            //Image d'échec de chargement qui sera utilisé par la preview
            error = painterResource(R.drawable.error),
            //Image d'attente.
            //placeholder = painterResource(R.drawable.toto),

            onError = { println(it) },
            modifier = Modifier
                .heightIn(max = 100.dp)
                .widthIn(max = 100.dp)
        )

        Column(modifier = Modifier.padding(4.dp)) {
            Text(text = data.name,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp)
            Text(text = data.getResume().take(20) + "...",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp)
        }
    }
}
