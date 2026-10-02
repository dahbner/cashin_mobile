package org.ucb.cashin_mobile.home.presentation.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

private val bottomBarIcons: List<ImageVector> = listOf(
    Icons.Filled.Home,
    Icons.AutoMirrored.Filled.ReceiptLong,
    Icons.Filled.Add,
    Icons.Filled.Flag,
    Icons.Filled.Person
)

@Composable
fun CashinBottomBar(modifier: Modifier = Modifier) {
    NavigationBar(modifier = modifier) {
        bottomBarIcons.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = index == 0,
                onClick = {},
                icon = { Icon(imageVector = icon, contentDescription = null) }
            )
        }
    }
}
