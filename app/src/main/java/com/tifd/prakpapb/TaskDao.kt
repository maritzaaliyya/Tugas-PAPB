package com.tifd.prakpapb

import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: TaskEntity)

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskEntity>

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("SELECT * FROM tasks WHERE is_done = :isDone")
    suspend fun getTasksByStatus(isDone: Boolean): List<TaskEntity>
}