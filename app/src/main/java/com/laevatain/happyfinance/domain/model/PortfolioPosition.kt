package com.laevatain.happyfinance.domain.model

data class PortfolioPosition(
    val id: Long = 0,
    val name: String,
    val description: String,
    val currentPrice: Double,
    val returnPercent: Double,
    val type: AssetType
)

enum class AssetType {
    ETF, CRYPTO, STOCK, FUND
}