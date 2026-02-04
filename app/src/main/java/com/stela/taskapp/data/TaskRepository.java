package com.stela.taskapp.data;

import com.stela.taskapp.javadb.AppDatabase;
import com.stela.taskapp.javadb.TaskDao;
import com.stela.taskapp.model.Task;

import java.util.ArrayList;


import android.content.Context;

import java.util.List;

public class TaskRepository {

    private static TaskRepository instance;
    private final TaskDao taskDao;
    private String text;

    private TaskRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        taskDao = db.taskDao();
    }

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
