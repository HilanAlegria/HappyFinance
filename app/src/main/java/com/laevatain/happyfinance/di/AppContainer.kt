package com.laevatain.happyfinance.di

import android.content.Context
import androidx.room.Room
import com.laevatain.happyfinance.data.local.database.AppDatabase
import com.laevatain.happyfinance.data.repository.BudgetRepositoryImpl
import com.laevatain.happyfinance.data.repository.GoalRepositoryImpl
import com.laevatain.happyfinance.data.repository.PortfolioRepositoryImpl
import com.laevatain.happyfinance.data.repository.TransactionRepositoryImpl
import com.laevatain.happyfinance.data.repository.TransferRepositoryImpl
import com.laevatain.happyfinance.domain.repository.BudgetRepository
import com.laevatain.happyfinance.domain.repository.GoalRepository
import com.laevatain.happyfinance.domain.repository.PortfolioRepository
import com.laevatain.happyfinance.domain.repository.TransactionRepository
import com.laevatain.happyfinance.domain.repository.TransferRepository

class AppContainer(context: Context) {

    private val database: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "happyfinance.db"
    ).build()

    val transactionRepository: TransactionRepository = TransactionRepositoryImpl(
        transactionDao = database.transactionDao(),
        budgetCategoryDao = database.budgetCategoryDao()
    )

    val budgetRepository: BudgetRepository = BudgetRepositoryImpl(
        budgetCategoryDao = database.budgetCategoryDao()
    )

    val portfolioRepository: PortfolioRepository = PortfolioRepositoryImpl(
        portfolioPositionDao = database.portfolioPositionDao()
    )

    val goalRepository: GoalRepository = GoalRepositoryImpl(
        goalDao = database.goalDao()
    )

    val transferRepository: TransferRepository = TransferRepositoryImpl(
        transferDao = database.transferDao()
    )
}