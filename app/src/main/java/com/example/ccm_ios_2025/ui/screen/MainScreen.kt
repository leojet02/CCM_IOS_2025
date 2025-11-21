package com.example.ccm_ios_2025.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(onButtonClick: () -> Unit) {
    val scope = rememberCoroutineScope()
    var clicked by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenue!", fontSize = 32.sp, color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Animation de scale
            val scale by animateFloatAsState(
                targetValue = if (clicked) 1.2f else 1f, animationSpec = tween(durationMillis = 200)
            )

            // Animation de couleur du bouton
            val btnColor by animateColorAsState(
                targetValue = if (clicked) Color(0xFF03DAC5) else Color(0xFF6200EE),
                animationSpec = tween(durationMillis = 200)
            )

            Button(
                onClick = {
                    scope.launch {
                        clicked = true
                        delay(300) // attente que l'animation se termine
                        clicked = false
                        onButtonClick() // navigation
                    }
                },
                modifier = Modifier
                    .size(width = 180.dp, height = 60.dp)
                    .graphicsLayer(scaleX = scale, scaleY = scale),
                colors = ButtonDefaults.buttonColors(containerColor = btnColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Go!Go!Go", color = Color.White
                )
            }
        }
    }
}
