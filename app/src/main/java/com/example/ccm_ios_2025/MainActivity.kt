package com.example.ccm_ios_2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ccm_ios_2025.ui.navigation.ApplicationNavHost
import com.example.ccm_ios_2025.ui.theme.CCM_IOS_2025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CCM_IOS_2025Theme {
                ApplicationNavHost() // <-- utiliser le nav host ici
            }
        }
    }
}
