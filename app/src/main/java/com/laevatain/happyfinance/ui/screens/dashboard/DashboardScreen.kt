package com.laevatain.happyfinance.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laevatain.happyfinance.ui.theme.AppTheme
import com.laevatain.happyfinance.ui.theme.Brand
import com.laevatain.happyfinance.ui.theme.Danger

data class QuickAction(
    val label: String,
    val icon: ImageVector,
    val action: () -> Unit
)

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    val colors = AppTheme.colors
    var showTransactionForm by remember { mutableStateOf(false) }

    val transactions = TransactionRepository.transactions
    val balance = TransactionRepository.balance
    val totalIncome = TransactionRepository.totalIncome
    val totalExpense = TransactionRepository.totalExpense

    val quickActions = listOf(
        QuickAction("Transferir", Icons.Default.SwapHoriz) {},
        QuickAction("Analizar", Icons.Default.Analytics) {},
        QuickAction("Metas", Icons.Default.Stars) {},
        QuickAction("Registrar", Icons.Default.Add) { showTransactionForm = true }
    )

    Scaffold(
        modifier = modifier,
        containerColor = colors.bg,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showTransactionForm = true },
                containerColor = Brand,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Registrar")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Buenos dias", color = colors.textSecondary, fontSize = 12.sp)
                    Text("Bienvenido", color = colors.textPrimary, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(colors.surface)
                ) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notificaciones", tint = colors.textPrimary)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        Brush.horizontalGradient(colors = listOf(Brand, Color(0xFF15B87E)))
                    )
                    .padding(24.dp)
            ) {
                Column {
                    Text("Patrimonio neto", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("$ ${"%,.0f".format(balance)}", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        Column {
                            Text("Ingresos del mes", color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("$ ${"%,.0f".format(totalIncome)}", color = Color(0xFFa8f0d8), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                        }
                        Spacer(modifier = Modifier.width(32.dp))
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(36.dp)
                                .background(Color.White.copy(alpha = 0.2f))
                        )
                        Spacer(modifier = Modifier.width(32.dp))
                        Column {
                            Text("Gastos del mes", color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("$ ${"%,.0f".format(totalExpense)}", color = Color(0xFFfca5a5), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                quickActions.forEach { action ->
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = colors.surface),
                        onClick = action.action
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = action.icon,
                                contentDescription = action.label,
                                tint = Brand,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(action.label, color = colors.textPrimary, fontSize = 10.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                "Ultimas transacciones",
                color = colors.textPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (transactions.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(colors.surface)
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Sin transacciones", color = colors.textPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Toca Registrar o el boton + para agregar tu primera transaccion",
                            color = colors.textSecondary,
                            fontSize = 11.sp
                        )
                    }
                }
            } else {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    transactions.take(10).forEach { transaction ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(colors.surface)
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (transaction.isIncome) Brand.copy(alpha = 0.15f)
                                            else Danger.copy(alpha = 0.15f)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (transaction.isIncome) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                                        contentDescription = null,
                                        tint = if (transaction.isIncome) Brand else Danger,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        transaction.name,
                                        color = colors.textPrimary,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(transaction.category, color = colors.textSecondary, fontSize = 11.sp)
                                }
                            }
                            Text(
                                "${if (transaction.isIncome) "+" else "-"} $ ${"%,.0f".format(transaction.amount)}",
                                color = if (transaction.isIncome) Brand else Danger,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        if (showTransactionForm) {
            TransactionFormSheet(
                onDismiss = { showTransactionForm = false },
                onSave = { name, amount, isIncome, category ->
                    TransactionRepository.add(name, amount, isIncome, category)
                    showTransactionForm = false
                }
            )
        }
    }
}