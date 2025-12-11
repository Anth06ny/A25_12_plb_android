package com.amonteiro.a25_12_plb_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.amonteiro.a25_12_plb_android.presentation.AppNavigation
import com.amonteiro.a25_12_plb_android.presentation.ui.theme.A25_12_plb_androidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()

        setContent {
            A25_12_plb_androidTheme {
                AppNavigation()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }
}
