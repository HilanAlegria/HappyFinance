package com.laevatain.happyfinance.data.repository

import com.laevatain.happyfinance.data.local.dao.PortfolioPositionDao
import com.laevatain.happyfinance.data.local.entity.PortfolioPositionEntity
import com.laevatain.happyfinance.domain.model.AssetType
import com.laevatain.happyfinance.domain.model.PortfolioPosition
import com.laevatain.happyfinance.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PortfolioRepositoryImpl(
    private val portfolioPositionDao: PortfolioPositionDao
) : PortfolioRepository {

    override fun getAllPositions(): Flow<List<PortfolioPosition>> =
        portfolioPositionDao.getAllPositions().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun insertPosition(position: PortfolioPosition) {
        portfolioPositionDao.insertPosition(position.toEntity())
    }

    override suspend fun deletePosition(id: Long) {
        portfolioPositionDao.deletePosition(id)
    }
}

fun PortfolioPositionEntity.toDomain() = PortfolioPosition(
    id = id,
    name = name,
    description = description,
    currentPrice = currentPrice,
    returnPercent = returnPercent,
    type = AssetType.valueOf(type)
)

fun PortfolioPosition.toEntity() = PortfolioPositionEntity(
    id = id,
    name = name,
    description = description,
    currentPrice = currentPrice,
    returnPercent = returnPercent,
    type = type.name
)