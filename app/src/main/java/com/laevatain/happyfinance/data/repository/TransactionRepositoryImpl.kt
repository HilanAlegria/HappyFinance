package com.laevatain.happyfinance.data.repository

import com.laevatain.happyfinance.data.local.dao.BudgetCategoryDao
import com.laevatain.happyfinance.data.local.dao.TransactionDao
import com.laevatain.happyfinance.data.local.entity.TransactionEntity
import com.laevatain.happyfinance.domain.model.Transaction
import com.laevatain.happyfinance.domain.model.TransactionType
import com.laevatain.happyfinance.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
    private val budgetCategoryDao: BudgetCategoryDao
) : TransactionRepository {

    override fun getAllTransactions(): Flow<List<Transaction>> =
        transactionDao.getAllTransactions().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun insertTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction.toEntity())

        if (transaction.type == TransactionType.EXPENSE) {
            val categories = budgetCategoryDao.getAllCategories()
        }
    }

    override suspend fun deleteTransaction(id: Long) {
        transactionDao.deleteTransaction(id)
    }
}

fun TransactionEntity.toDomain() = Transaction(
    id = id,
    name = name,
    category = category,
    amount = amount,
    type = if (type == "INCOME") TransactionType.INCOME else TransactionType.EXPENSE,
    date = date,
    icon = icon
)

fun Transaction.toEntity() = TransactionEntity(
    id = id,
    name = name,
    category = category,
    amount = amount,
    type = type.name,
    date = date,
    icon = icon
)