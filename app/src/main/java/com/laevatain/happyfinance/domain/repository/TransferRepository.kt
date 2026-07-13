package com.laevatain.happyfinance.domain.repository

import com.laevatain.happyfinance.domain.model.Transfer
import kotlinx.coroutines.flow.Flow

interface TransferRepository {
    fun getAllTransfers(): Flow<List<Transfer>>
    suspend fun insertTransfer(transfer: Transfer)
}