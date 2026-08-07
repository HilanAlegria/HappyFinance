package com.laevatain.happyfinance.ui.screens.dashboard

import androidx.compose.runtime.mutableStateListOf

data class TransactionUi(
    val id: Int,
    val name: String,
    val amount: Double,
    val isIncome: Boolean,
    val category: String,
    val date: String
)

object TransactionRepository {
    val transactions = mutableStateListOf<TransactionUi>()
    private var nextId = 1

    fun add(name: String, amount: Double, isIncome: Boolean, category: String) {
        transactions.add(0,
            TransactionUi(
                id = nextId++,
                name = name,
                amount = amount,
                isIncome = isIncome,
                category = category,
                date = java.time.LocalDate.now().toString()
            )
        )
    }

    val totalIncome get() = transactions.filter { it.isIncome }.sumOf { it.amount }
    val totalExpense get() = transactions.filter { !it.isIncome }.sumOf { it.amount }
    val balance get() = totalIncome - totalExpense
}