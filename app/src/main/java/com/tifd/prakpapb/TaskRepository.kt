package com.tifd.prakpapb

class TaskRepository(private val taskDao: TaskDao) {
    suspend fun insertTask(task: TaskEntity) = taskDao.insertTask(task)
    suspend fun updateTask(task: TaskEntity) = taskDao.updateTask(task)
    suspend fun getAllTasks() = taskDao.getAllTasks()
}