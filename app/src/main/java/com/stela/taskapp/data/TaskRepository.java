package com.stela.taskapp.data;

import com.stela.taskapp.data.javadb.AppDatabase;
import com.stela.taskapp.data.javadb.TaskDao;
import com.stela.taskapp.model.Category;
import com.stela.taskapp.model.Task;


import android.content.Context;

import androidx.room.Dao;

import java.util.List;

public class TaskRepository {

    private static TaskRepository instance;
    private final TaskDao taskDao;
    private String text;


    public static synchronized TaskRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TaskRepository(context);
        }
        return instance;
    }

    // LISTAR
    public List<Task> getTasks() {
        return taskDao.getAll();
    }



    public TaskRepository(Context context) {
        taskDao = AppDatabase.getInstance(context).taskDao();
    }

    public List<Task> getAll() {
        return taskDao.getAll();
    }

    public List<Task> getByCategory(Category category) {
        return taskDao.getByCategory(category);
    }


    // INSERIR
    public void addTask(Task task) {
        taskDao.insert(task);
    }

    // ATUALIZAR
    public void updateTask(Task task) {
        taskDao.update(task);
    }

    // REMOVER
    public void removeTask(Task task) {
        taskDao.delete(task);
    }

    // LIMPAR TUDO
    public void clear() {
        taskDao.deleteAll();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    }

