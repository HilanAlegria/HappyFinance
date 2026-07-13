package com.laevatain.happyfinance.domain.repository

import com.laevatain.happyfinance.domain.model.PortfolioPosition
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    fun getAllPositions(): Flow<List<PortfolioPosition>>
    suspend fun insertPosition(position: PortfolioPosition)
    suspend fun deletePosition(id: Long)
}