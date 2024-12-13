package com.example.core_api.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core_api.database.dao.IncomeCategoryDao

@Entity(tableName = IncomeCategoryDao.TABLE_NAME)
data class IncomeCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long = 0,
    val name: String
)
