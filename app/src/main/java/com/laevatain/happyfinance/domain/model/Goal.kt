package com.laevatain.happyfinance.domain.model

data class Goal(
    val id: Long = 0,
    val name: String,
    val description: String,
    val targetAmount: Double,
    val savedAmount: Double,
    val deadline: String,
    val status: GoalStatus
)

enum class GoalStatus {
    ACTIVE, COMPLETED
}