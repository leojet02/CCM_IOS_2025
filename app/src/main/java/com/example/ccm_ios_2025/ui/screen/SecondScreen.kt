package com.example.ccm_ios_2025.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ccm_ios_2025.ui.model.ItemUi
import com.example.ccm_ios_2025.ui.viewmodel.AndroidVersionViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navigateBack: () -> Unit) {
    val viewModel: AndroidVersionViewModel = viewModel()
    val listOfResult = viewModel.androidVersionList.collectAsState().value
    var IncrementNumber by remember { mutableStateOf(0) }
    var IncrementNumber2 by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Second Screen") }, navigationIcon = {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            })
        },

        bottomBar = {
            BottomAppBar {
                Button(
                    modifier = Modifier.weight(1f),
                    content = { Text("Add") },
                    onClick = { viewModel.insertAndroidVersion() }
                )
                Button(
                    modifier = Modifier.weight(1f),
                    content = { Text("Delete") },
                    onClick = { viewModel.deleteAllAndroidVersion() }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Hello Android!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Bouton incrémental", fontWeight = FontWeight.Bold)

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { IncrementNumber = Increment(IncrementNumber) },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(IncrementNumber.toString())
                    }
                }
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Maintien pour +2", fontWeight = FontWeight.Bold)

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(top = 8.dp)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(12.dp)
                            )
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = {
                                        var isTap = true
                                        job = scope.launch {
                                            delay(500)
                                            isTap = false
                                            while (isActive) {
                                                IncrementNumber2 = Increment(IncrementNumber2, 2)
                                                delay(200)
                                            }
                                        }
                                        tryAwaitRelease()
                                        job?.cancel()
                                        if (isTap) IncrementNumber2 = Increment(IncrementNumber2)
                                    })
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            IncrementNumber2.toString(),
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(listOfResult) { item ->
                        when (item) {

                            // HEADER
                            is ItemUi.Header -> Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                                    .background(
                                        MaterialTheme.colorScheme.surfaceVariant,
                                        RoundedCornerShape(16.dp)
                                    )
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    "Version : ${item.title}",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Image(
                                    painter = rememberAsyncImagePainter(item.image),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(128.dp)
                                        .padding(top = 8.dp)
                                )
                            }

                            // ITEM
                            is ItemUi.Item -> Text(
                                "• Version ${item.title}",
                                style = MaterialTheme.typography.bodyLarge
                            )

                            // FOOTER
                            is ItemUi.Footer -> Text(
                                "Nombre d’éléments : ${item.numberOf}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

fun Increment(Number: Int? = 0, qte: Int? = 1): Int {
    return (Number ?: 0) + (qte ?: 1)
}