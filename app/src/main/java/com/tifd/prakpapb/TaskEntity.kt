package com.tifd.prakpapb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "nama_mk") val namaMk: String,
    @ColumnInfo(name = "detail") val detail: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "is_done") val isDone: Boolean = false // Add is_done field
)