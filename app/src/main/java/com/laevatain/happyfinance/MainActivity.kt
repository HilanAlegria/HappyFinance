package com.laevatain.happyfinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.laevatain.happyfinance.ui.navigation.AppNavigation
import com.laevatain.happyfinance.ui.theme.HappyFinanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyFinanceTheme {
                AppNavigation()
            }
        }
    }
}