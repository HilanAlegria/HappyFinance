package com.laevatain.happyfinance.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transfers")
data class TransferEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fromAccount: String,
    val toAccount: String,
    val amount: Double,
    val date: String,
    val note: String
)