package com.tifd.prakpapb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> get() = _tasks

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            _tasks.value = repository.getAllTasks()
        }
    }

    fun addTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.insertTask(task)
            fetchTasks()
        }
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task)
            fetchTasks()
        }
    }
}