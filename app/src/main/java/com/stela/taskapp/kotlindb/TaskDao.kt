package com.stela.taskapp.kotlindb

import androidx.room.Insert;
import com.stela.taskapp.model.Task
import androidx.room.Query

interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Query
    suspend fun getAllTasks(): List<Task>




}