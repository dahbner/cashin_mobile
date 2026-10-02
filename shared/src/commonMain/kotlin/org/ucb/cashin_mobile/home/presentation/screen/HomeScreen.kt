package org.ucb.cashin_mobile.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.ucb.cashin_mobile.home.presentation.composable.CashinBottomBar
import org.ucb.cashin_mobile.login.presentation.composable.CashinColors
import org.ucb.cashin_mobile.login.presentation.composable.PrimaryButton
import org.ucb.cashin_mobile.navigation.NavRoute

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = CashinColors.Background,
        bottomBar = { CashinBottomBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {}
            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(text = "Consejo", onClick = { navController.navigate(NavRoute.Consejo) })
            PrimaryButton(text = "Pregunta", onClick = { navController.navigate(NavRoute.Pregunta) })
            PrimaryButton(text = "Desafios", onClick = { navController.navigate(NavRoute.Desafios) })
        }
    }
}
