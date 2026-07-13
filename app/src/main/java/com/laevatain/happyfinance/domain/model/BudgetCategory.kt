package com.laevatain.happyfinance.domain.model

data class BudgetCategory(
    val id: Long = 0,
    val name: String,
    val icon: String,
    val spent: Double,
    val limit: Double,
    val transactionCount: Int
)