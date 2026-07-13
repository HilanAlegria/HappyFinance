package com.laevatain.happyfinance.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.laevatain.happyfinance.data.local.entity.BudgetCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetCategoryDao {
    @Query("SELECT * FROM budget_categories")
    fun getAllCategories(): Flow<List<BudgetCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: BudgetCategoryEntity)

    @Update
    suspend fun updateCategory(category: BudgetCategoryEntity)

    @Query("UPDATE budget_categories SET spent = 0, transactionCount = 0")
    suspend fun resetMonthlySpending()

    @Query("SELECT COUNT(*) FROM budget_categories")
    suspend fun getCount(): Int
}