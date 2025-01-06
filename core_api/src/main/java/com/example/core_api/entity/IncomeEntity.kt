package com.example.core_api.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.core_api.database.dao.IncomesDao
import java.util.Date

@Entity(
    tableName = IncomesDao.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = IncomeCategoryEntity::class,
            parentColumns = arrayOf("categoryId"),
            childColumns = arrayOf("categoryId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class IncomeEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Date,
    val time: Date,
    val categoryId: Long,
    val amount: Double,
    val currency: String,
    val comment: String
)