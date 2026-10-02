package org.ucb.cashin_mobile.expenses.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.TrendingUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ucb.cashin_mobile.expenses.presentation.model.ExpenseCategoryUi

private object ExpensesColors {
    val Background = Color(0xFFFFF9EC)
    val DarkGreen = Color(0xFF075D36)
    val MediumGreen = Color(0xFF56A34D)
    val CategoryGreen = Color(0xFF8DBE84)
    val ProgressGreen = Color(0xFFB4D5AA)
    val ProgressTrack = Color(0xFFFFFCF3)
    val SelectedNavigation = Color(0xFFD9D9D9)
}

private val sampleExpenses = listOf(
    ExpenseCategoryUi(name = "alimentacion", amount = "BS 245", progress = 0.54f),
    ExpenseCategoryUi(name = "alimentacion", amount = "BS 245", progress = 0.39f),
    ExpenseCategoryUi(name = "alimentacion", amount = "BS 245", progress = 0.74f),
    ExpenseCategoryUi(name = "alimentacion", amount = "BS 245", progress = 0.23f),
    ExpenseCategoryUi(name = "alimentacion", amount = "BS 245", progress = 0.23f)
)

@Composable
fun ExpensesScreen(
    onBackClick: () -> Unit = {},
    onReportClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = ExpensesColors.Background,
        bottomBar = { ExpensesBottomBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = ExpensesColors.DarkGreen,
                    modifier = Modifier.size(38.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Gastos",
                color = ExpensesColors.DarkGreen,
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 46.sp,
                modifier = Modifier.padding(horizontal = 18.dp)
            )
            Text(
                text = "Agosto 2026",
                color = ExpensesColors.DarkGreen,
                fontSize = 27.sp,
                lineHeight = 32.sp,
                modifier = Modifier.padding(horizontal = 18.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            TotalSpentCard(modifier = Modifier.padding(horizontal = 18.dp))

            Spacer(modifier = Modifier.height(54.dp))

            Column(verticalArrangement = Arrangement.spacedBy(27.dp)) {
                sampleExpenses.forEach { expense ->
                    ExpenseCategoryRow(expense = expense)
                }
            }

            Spacer(modifier = Modifier.height(38.dp))

            Button(
                onClick = onReportClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ExpensesColors.MediumGreen,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 38.dp)
            ) {
                Text(text = "Ver reporte", fontSize = 26.sp, fontWeight = FontWeight.Normal)
            }

            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Composable
private fun TotalSpentCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = ExpensesColors.Background,
        shape = RoundedCornerShape(18.dp),
        shadowElevation = 5.dp
    ) {
        Column(modifier = Modifier.padding(horizontal = 22.dp, vertical = 20.dp)) {
            Text(
                text = "Total gastado",
                color = ExpensesColors.DarkGreen,
                fontSize = 23.sp
            )
            Text(
                text = "Bs 1,000.00",
                color = ExpensesColors.DarkGreen,
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 64.sp
            )
            Text(
                text = "vs Julio 10%",
                color = ExpensesColors.DarkGreen,
                fontSize = 26.sp
            )
        }
    }
}

@Composable
private fun ExpenseCategoryRow(expense: ExpenseCategoryUi) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(58.dp)
                .clip(CircleShape)
                .background(ExpensesColors.CategoryGreen)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = expense.name,
                color = ExpensesColors.DarkGreen,
                fontSize = 22.sp,
                lineHeight = 28.sp
            )
            LinearProgressIndicator(
                progress = { expense.progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(14.dp)
                    .clip(RoundedCornerShape(8.dp)),
                color = ExpensesColors.ProgressGreen,
                trackColor = ExpensesColors.ProgressTrack,
                gapSize = 0.dp,
                drawStopIndicator = {}
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = expense.amount,
            color = ExpensesColors.DarkGreen,
            fontSize = 21.sp
        )
    }
}

@Composable
private fun ExpensesBottomBar() {
    Surface(color = Color.White, shadowElevation = 5.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(110.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationItem(label = "Aventura", icon = Icons.Outlined.Home)
            NavigationItem(
                label = "Gastos",
                icon = Icons.Outlined.CalendarMonth,
                selected = true
            )
            AddExpenseItem()
            NavigationItem(label = "Metas", icon = Icons.AutoMirrored.Outlined.TrendingUp)
            NavigationItem(label = "Perfil", icon = Icons.Outlined.Person)
        }
    }
}

@Composable
private fun NavigationItem(
    label: String,
    icon: ImageVector,
    selected: Boolean = false
) {
    Column(
        modifier = Modifier
            .width(78.dp)
            .height(110.dp)
            .background(if (selected) ExpensesColors.SelectedNavigation else Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = ExpensesColors.DarkGreen,
            modifier = Modifier.size(42.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = label,
            color = ExpensesColors.DarkGreen,
            fontSize = 14.sp,
            maxLines = 1
        )
    }
}

@Composable
private fun AddExpenseItem() {
    Box(
        modifier = Modifier
            .size(82.dp)
            .background(ExpensesColors.DarkGreen, CircleShape)
            .border(5.dp, ExpensesColors.MediumGreen, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Agregar gasto",
            tint = ExpensesColors.MediumGreen,
            modifier = Modifier.size(48.dp)
        )
    }
}
