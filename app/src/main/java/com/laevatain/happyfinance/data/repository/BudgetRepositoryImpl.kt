package com.laevatain.happyfinance.data.repository

import com.laevatain.happyfinance.data.local.dao.BudgetCategoryDao
import com.laevatain.happyfinance.data.local.entity.BudgetCategoryEntity
import com.laevatain.happyfinance.domain.model.BudgetCategory
import com.laevatain.happyfinance.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BudgetRepositoryImpl(
    private val budgetCategoryDao: BudgetCategoryDao
) : BudgetRepository {

    override fun getAllCategories(): Flow<List<BudgetCategory>> =
        budgetCategoryDao.getAllCategories().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun updateCategory(category: BudgetCategory) {
        budgetCategoryDao.updateCategory(category.toEntity())
    }

    override suspend fun resetMonthlySpending() {
        budgetCategoryDao.resetMonthlySpending()
    }
}

fun BudgetCategoryEntity.toDomain() = BudgetCategory(
    id = id,
    name = name,
    icon = icon,
    spent = spent,
    limit = limit,
    transactionCount = transactionCount
)

fun BudgetCategory.toEntity() = BudgetCategoryEntity(
    id = id,
    name = name,
    icon = icon,
    spent = spent,
    limit = limit,
    transactionCount = transactionCount
)