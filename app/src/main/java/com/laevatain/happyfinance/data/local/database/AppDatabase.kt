package com.laevatain.happyfinance.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laevatain.happyfinance.data.local.dao.BudgetCategoryDao
import com.laevatain.happyfinance.data.local.dao.GoalDao
import com.laevatain.happyfinance.data.local.dao.PortfolioPositionDao
import com.laevatain.happyfinance.data.local.dao.TransactionDao
import com.laevatain.happyfinance.data.local.dao.TransferDao
import com.laevatain.happyfinance.data.local.entity.BudgetCategoryEntity
import com.laevatain.happyfinance.data.local.entity.GoalEntity
import com.laevatain.happyfinance.data.local.entity.PortfolioPositionEntity
import com.laevatain.happyfinance.data.local.entity.TransactionEntity
import com.laevatain.happyfinance.data.local.entity.TransferEntity

@Database(
    entities = [
        TransactionEntity::class,
        BudgetCategoryEntity::class,
        PortfolioPositionEntity::class,
        GoalEntity::class,
        TransferEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun budgetCategoryDao(): BudgetCategoryDao
    abstract fun portfolioPositionDao(): PortfolioPositionDao
    abstract fun goalDao(): GoalDao
    abstract fun transferDao(): TransferDao
}