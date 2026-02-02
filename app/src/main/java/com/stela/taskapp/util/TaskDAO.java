package com.stela.taskapp.util;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.stela.taskapp.model.Task;

import java.util.List;

@Dao
public interface TaskDAO {
@Query("SELECT * FROM tasks_table") {

    List<Task> getAllTasks();

    @Insert
    void insertTask(Task Object task;
    task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM tasks_table")
    void deleteAllTasks();
}



}
