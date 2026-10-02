package org.ucb.cashin_mobile.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.ucb.cashin_mobile.home.presentation.composable.CashinBottomBar
import org.ucb.cashin_mobile.home.presentation.composable.WireframeBox
import org.ucb.cashin_mobile.login.presentation.composable.CashinColors

private val challengeColors = listOf(Color.Green, Color.Cyan, Color.Yellow)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesafiosScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CashinColors.Background,
        topBar = {
            TopAppBar(
                title = { Text("Desafíos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = { CashinBottomBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WireframeBox(label = "Banner: Desafío destacado", color = Color.LightGray, height = 200.dp)
            challengeColors.forEachIndexed { index, color ->
                WireframeBox(
                    label = "Desafío ${index + 1}\n[ barra de progreso ]",
                    color = color,
                    height = 110.dp
                )
            }
        }
    }
}
