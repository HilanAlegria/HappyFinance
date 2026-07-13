package com.laevatain.happyfinance.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.laevatain.happyfinance.data.local.entity.PortfolioPositionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioPositionDao {
    @Query("SELECT * FROM portfolio_positions ORDER BY id DESC")
    fun getAllPositions(): Flow<List<PortfolioPositionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosition(position: PortfolioPositionEntity)

    @Query("DELETE FROM portfolio_positions WHERE id = :id")
    suspend fun deletePosition(id: Long)
}