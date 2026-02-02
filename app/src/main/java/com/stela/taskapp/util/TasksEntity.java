package com.stela.taskapp.util;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;


@Entity(tableName = "tasks_table")

public class TasksEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String taskName;
    private String taskDescription;
    private String taskDate;
    private String taskPriority;
    private String taskState;

    @NonNull
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(@NonNull String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }
}
