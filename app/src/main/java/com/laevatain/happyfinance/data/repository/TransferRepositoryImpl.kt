package com.laevatain.happyfinance.data.repository

import com.laevatain.happyfinance.data.local.dao.TransferDao
import com.laevatain.happyfinance.data.local.entity.TransferEntity
import com.laevatain.happyfinance.domain.model.Transfer
import com.laevatain.happyfinance.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransferRepositoryImpl(
    private val transferDao: TransferDao
) : TransferRepository {

    override fun getAllTransfers(): Flow<List<Transfer>> =
        transferDao.getAllTransfers().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun insertTransfer(transfer: Transfer) {
        transferDao.insertTransfer(transfer.toEntity())
    }
}

fun TransferEntity.toDomain() = Transfer(
    id = id,
    fromAccount = fromAccount,
    toAccount = toAccount,
    amount = amount,
    date = date,
    note = note
)

fun Transfer.toEntity() = TransferEntity(
    id = id,
    fromAccount = fromAccount,
    toAccount = toAccount,
    amount = amount,
    date = date,
    note = note
)