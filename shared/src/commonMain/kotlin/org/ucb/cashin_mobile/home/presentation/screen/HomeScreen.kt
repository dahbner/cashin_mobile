package org.ucb.cashin_mobile.home.presentation.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.ucb.cashin_mobile.home.presentation.composable.CashinBottomBar
import org.ucb.cashin_mobile.home.presentation.composable.WireframeBox
import org.ucb.cashin_mobile.login.presentation.composable.CashinColors
import org.ucb.cashin_mobile.login.presentation.composable.PrimaryButton
import org.ucb.cashin_mobile.navigation.NavRoute

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CashinColors.Background,
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
            WireframeBox(label = "Saludo + Perrito Cashi", color = Color.LightGray, height = 180.dp)
            WireframeBox(label = "Tarjeta de Saldo\nBs 1,000.00", color = Color.Green, height = 140.dp)
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                WireframeBox(
                    label = "Ingresos",
                    color = Color.Cyan,
                    height = 100.dp,
                    modifier = Modifier.weight(1f)
                )
                WireframeBox(
                    label = "Gastos",
                    color = Color.Yellow,
                    height = 100.dp,
                    modifier = Modifier.weight(1f)
                )
            }

            PrimaryButton(text = "Consejo Cashi", onClick = { navController.navigate(NavRoute.Consejo) })
            PrimaryButton(text = "Pregunta Cashi", onClick = { navController.navigate(NavRoute.Pregunta) })
            PrimaryButton(text = "Desafíos", onClick = { navController.navigate(NavRoute.Desafios) })
            PrimaryButton(text = "Login", onClick = { navController.navigate(NavRoute.Login) })
        }
    }
}
