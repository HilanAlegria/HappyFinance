package com.laevatain.happyfinance.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portfolio_positions")
data class PortfolioPositionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val currentPrice: Double,
    val returnPercent: Double,
    val type: String
)