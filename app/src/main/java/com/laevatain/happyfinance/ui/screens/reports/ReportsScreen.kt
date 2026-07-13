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
import com.laevatain.happyfinance.ui.theme.AppTheme
import com.laevatain.happyfinance.ui.theme.Brand
import com.laevatain.happyfinance.ui.theme.Danger
import com.laevatain.happyfinance.ui.theme.Warning

@Composable
fun ReportsScreen(modifier: Modifier = Modifier) {
    val colors = AppTheme.colors

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
                Triple("Ingresos", "$ 0", Brand),
                Triple("Egresos", "$ 0", Danger)
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
                Triple("Balance neto", "$ 0", Brand),
                Triple("Tasa de ahorro", "0%", Warning)
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

        Text("Resumen", color = colors.textPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(colors.surface)
                .padding(40.dp)
        ) {
            Column {
                Text(
                    "Sin datos aun",
                    color = colors.textPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Registra transacciones para ver tu resumen financiero aqui",
                    color = colors.textSecondary,
                    fontSize = 11.sp
                )
            }
        }
    }
}