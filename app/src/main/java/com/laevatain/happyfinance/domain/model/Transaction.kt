package com.laevatain.happyfinance.domain.model

data class Transaction(
    val id: Long = 0,
    val name: String,
    val category: String,
    val amount: Double,
    val type: TransactionType,
    val date: String,
    val icon: String
)

enum class TransactionType {
    INCOME, EXPENSE
}