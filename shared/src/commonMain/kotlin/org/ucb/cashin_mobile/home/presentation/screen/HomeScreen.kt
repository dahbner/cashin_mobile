package org.ucb.cashin_mobile.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ucb.cashin_mobile.login.presentation.composable.CashinColors

@Composable
fun HomeScreen(onExpensesClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CashinColors.Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenido a Cashin", fontSize = 24.sp, color = CashinColors.DarkGreen)
        Button(
            onClick = onExpensesClick,
            colors = ButtonDefaults.buttonColors(containerColor = CashinColors.Green),
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text(text = "Ver gastos")
        }
    }
}
