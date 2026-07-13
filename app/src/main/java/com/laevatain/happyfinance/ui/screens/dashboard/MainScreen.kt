package com.laevatain.happyfinance.ui.screens.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShowChart
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laevatain.happyfinance.ui.screens.expenses.ExpensesScreen
import com.laevatain.happyfinance.ui.screens.portfolio.PortfolioScreen
import com.laevatain.happyfinance.ui.screens.profile.ProfileScreen
import com.laevatain.happyfinance.ui.screens.reports.ReportsScreen
import com.laevatain.happyfinance.ui.theme.AppTheme
import com.laevatain.happyfinance.ui.theme.Brand

data class TabItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun MainScreen(onLogout: () -> Unit) {
    val colors = AppTheme.colors

    val tabs = listOf(
        TabItem("Inicio", Icons.Filled.Home, Icons.Outlined.Home),
        TabItem("Gastos", Icons.Filled.Warning, Icons.Outlined.Warning),
        TabItem("Cartera", Icons.Filled.ShowChart, Icons.Outlined.ShowChart),
        TabItem("Reportes", Icons.Filled.BarChart, Icons.Outlined.BarChart),
        TabItem("Perfil", Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle),
    )

    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = colors.surface,
                tonalElevation = 0.dp
            ) {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = {
                            Icon(
                                imageVector = if (selectedTab == index) tab.selectedIcon else tab.unselectedIcon,
                                contentDescription = tab.title
                            )
                        },
                        label = { Text(tab.title, fontSize = 10.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Brand,
                            selectedTextColor = Brand,
                            unselectedIconColor = colors.textSecondary,
                            unselectedTextColor = colors.textSecondary,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> DashboardScreen(modifier = Modifier.padding(innerPadding))
            1 -> ExpensesScreen(modifier = Modifier.padding(innerPadding))
            2 -> PortfolioScreen(modifier = Modifier.padding(innerPadding))
            3 -> ReportsScreen(modifier = Modifier.padding(innerPadding))
            4 -> ProfileScreen(modifier = Modifier.padding(innerPadding), onLogout = onLogout)
        }
    }
}