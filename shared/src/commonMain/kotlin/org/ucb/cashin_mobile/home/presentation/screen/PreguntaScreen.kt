package org.ucb.cashin_mobile.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.ucb.cashin_mobile.home.presentation.composable.WireframeBox
import org.ucb.cashin_mobile.login.presentation.composable.CashinColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreguntaScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CashinColors.Background,
        topBar = {
            TopAppBar(
                title = { Text("Pregunta a Cashi") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            WireframeBox(
                label = "Input: Escribe tu pregunta… ➤",
                color = Color.LightGray,
                height = 64.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WireframeBox(label = "Mensaje de Cashi", color = Color.Cyan, height = 120.dp)
            WireframeBox(
                label = "Mensaje del usuario",
                color = Color.Green,
                height = 80.dp,
                modifier = Modifier.fillMaxWidth(0.8f).align(Alignment.End)
            )
            WireframeBox(label = "Respuesta de Cashi", color = Color.Cyan, height = 200.dp)
        }
    }
}
