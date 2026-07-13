package com.laevatain.happyfinance.domain.repository

import com.laevatain.happyfinance.domain.model.BudgetCategory
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    fun getAllCategories(): Flow<List<BudgetCategory>>
    suspend fun updateCategory(category: BudgetCategory)
    suspend fun resetMonthlySpending()
}