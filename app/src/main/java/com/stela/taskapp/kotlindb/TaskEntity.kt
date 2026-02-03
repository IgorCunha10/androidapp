package com.stela.taskapp.kotlindb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")

data class TaskEntity(

    @PrimaryKey(autoGenerate = true)

    val id: Float,
    val taskName: String,
    val taskDescription: String,
    val taskDate: String,
    val taskPriority: String,
    val taskState: String
)




