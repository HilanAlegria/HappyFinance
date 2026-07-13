package com.laevatain.happyfinance.ui.screens.expenses

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laevatain.happyfinance.ui.theme.AppTheme
import com.laevatain.happyfinance.ui.theme.Brand
import com.laevatain.happyfinance.ui.theme.Danger
import com.laevatain.happyfinance.ui.theme.Warning

data class BudgetCategoryUi(
    val name: String,
    val icon: String,
    val spent: Double,
    val limit: Double
)

val defaultCategories = listOf(
    BudgetCategoryUi("Alimentacion", "🍽", 0.0, 600000.0),
    BudgetCategoryUi("Transporte", "🚗", 0.0, 200000.0),
    BudgetCategoryUi("Entretenimiento", "🎮", 0.0, 200000.0),
    BudgetCategoryUi("Salud", "💊", 0.0, 300000.0),
    BudgetCategoryUi("Educacion", "📚", 0.0, 400000.0),
)

fun getBudgetColor(spent: Double, limit: Double) = when {
    spent >= limit -> Danger
    spent >= limit * 0.7 -> Warning
    else -> Brand
}

@Composable
fun ExpensesScreen(modifier: Modifier = Modifier) {
    val colors = AppTheme.colors

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.bg)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Gastos y presupuestos", color = colors.textPrimary, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(colors.surface)
                .padding(16.dp)
        ) {
            Column {
                Text("Total gastado", color = colors.textSecondary, fontSize = 11.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text("$ 0", color = colors.textPrimary, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Presupuestos", color = colors.textPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(12.dp))

        defaultCategories.forEach { cat ->
            val ratio = if (cat.limit > 0) cat.spent / cat.limit else 0.0
            val barColor = getBudgetColor(cat.spent, cat.limit)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(colors.surface)
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(cat.icon, fontSize = 16.sp)
                        Text(
                            cat.name,
                            color = colors.textPrimary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp)
                        )
                        Text(
                            "$ ${cat.spent.toLong()} / $ ${cat.limit.toLong()}",
                            color = colors.textSecondary,
                            fontSize = 11.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(colors.border)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(fraction = ratio.toFloat().coerceIn(0f, 1f))
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(barColor)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        "${(ratio * 100).toInt()}%",
                        color = barColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    }
}