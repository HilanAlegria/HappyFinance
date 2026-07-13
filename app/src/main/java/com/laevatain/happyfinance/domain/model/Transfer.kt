package com.laevatain.happyfinance.domain.model

data class Transfer(
    val id: Long = 0,
    val fromAccount: String,
    val toAccount: String,
    val amount: Double,
    val date: String,
    val note: String
)