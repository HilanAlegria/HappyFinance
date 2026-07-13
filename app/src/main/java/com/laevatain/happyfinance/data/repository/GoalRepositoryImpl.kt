package com.laevatain.happyfinance.data.repository

import com.laevatain.happyfinance.data.local.dao.GoalDao
import com.laevatain.happyfinance.data.local.entity.GoalEntity
import com.laevatain.happyfinance.domain.model.Goal
import com.laevatain.happyfinance.domain.model.GoalStatus
import com.laevatain.happyfinance.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GoalRepositoryImpl(
    private val goalDao: GoalDao
) : GoalRepository {

    override fun getAllGoals(): Flow<List<Goal>> =
        goalDao.getAllGoals().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun insertGoal(goal: Goal) {
        goalDao.insertGoal(goal.toEntity())
    }

    override suspend fun updateGoal(goal: Goal) {
        goalDao.updateGoal(goal.toEntity())
    }

    override suspend fun deleteGoal(id: Long) {
        goalDao.deleteGoal(id)
    }
}

fun GoalEntity.toDomain() = Goal(
    id = id,
    name = name,
    description = description,
    targetAmount = targetAmount,
    savedAmount = savedAmount,
    deadline = deadline,
    status = GoalStatus.valueOf(status)
)

fun Goal.toEntity() = GoalEntity(
    id = id,
    name = name,
    description = description,
    targetAmount = targetAmount,
    savedAmount = savedAmount,
    deadline = deadline,
    status = status.name
)