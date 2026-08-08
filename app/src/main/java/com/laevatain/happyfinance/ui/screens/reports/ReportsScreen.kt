package com.laevatain.happyfinance.ui.screens.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laevatain.happyfinance.ui.screens.dashboard.TransactionRepository
import com.laevatain.happyfinance.ui.theme.AppTheme
import com.laevatain.happyfinance.ui.theme.Brand
import com.laevatain.happyfinance.ui.theme.Danger
import com.laevatain.happyfinance.ui.theme.Warning

@Composable
fun ReportsScreen(modifier: Modifier = Modifier) {
    val colors = AppTheme.colors
    val transactions = TransactionRepository.transactions
    val totalIncome = TransactionRepository.totalIncome
    val totalExpense = TransactionRepository.totalExpense
    val balance = TransactionRepository.balance
    val savingsRate = if (totalIncome > 0) (balance / totalIncome * 100).toInt() else 0

    val expensesByCategory = transactions
        .filter { !it.isIncome }
        .groupBy { it.category }
        .mapValues { it.value.sumOf { t -> t.amount } }
        .entries
        .sortedByDescending { it.value }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.bg)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Contabilidad", color = colors.textPrimary, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            listOf(
                Triple("Ingresos", "$ ${"%,.0f".format(totalIncome)}", Brand),
                Triple("Egresos", "$ ${"%,.0f".format(totalExpense)}", Danger)
            ).forEachIndexed { index, (label, value, color) ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = if (index == 0) 5.dp else 0.dp, start = if (index == 1) 5.dp else 0.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(colors.surface)
                        .padding(14.dp)
                ) {
                    Column {
                        Text(label, color = colors.textSecondary, fontSize = 11.sp)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(value, color = color, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            listOf(
                Triple("Balance neto", "$ ${"%,.0f".format(balance)}", if (balance >= 0) Brand else Danger),
                Triple("Tasa de ahorro", "$savingsRate%", Warning)
            ).forEachIndexed { index, (label, value, color) ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = if (index == 0) 5.dp else 0.dp, start = if (index == 1) 5.dp else 0.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(colors.surface)
                        .padding(14.dp)
                ) {
                    Column {
                        Text(label, color = colors.textSecondary, fontSize = 11.sp)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(value, color = color, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Gastos por categoria", color = colors.textPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(12.dp))

        if (expensesByCategory.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(colors.surface)
                    .padding(40.dp)
            ) {
                Column {
                    Text("Sin datos aun", color = colors.textPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Registra transacciones para ver tu resumen financiero aqui",
                        color = colors.textSecondary,
                        fontSize = 11.sp
                    )
                }
            }
        } else {
            val maxAmount = expensesByCategory.firstOrNull()?.value ?: 1.0

            expensesByCategory.forEach { (category, amount) ->
                val ratio = (amount / maxAmount).toFloat().coerceIn(0f, 1f)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(colors.surface)
                        .padding(14.dp)
                ) {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                category,
                                color = colors.textPrimary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                "$ ${"%,.0f".format(amount)}",
                                color = Danger,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(colors.border)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(fraction = ratio)
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Danger)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Historial completo", color = colors.textPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(12.dp))

        if (transactions.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(colors.surface)
                    .padding(20.dp)
            ) {
                Text("Sin transacciones registradas", color = colors.textSecondary, fontSize = 12.sp)
            }
        } else {
            transactions.forEach { transaction ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(colors.surface)
                        .padding(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(transaction.name, color = colors.textPrimary, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                        Text(transaction.category, color = colors.textSecondary, fontSize = 11.sp)
                    }
                    Text(
                        "${if (transaction.isIncome) "+" else "-"} $ ${"%,.0f".format(transaction.amount)}",
                        color = if (transaction.isIncome) Brand else Danger,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}