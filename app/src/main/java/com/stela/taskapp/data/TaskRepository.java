package com.stela.taskapp.data;

import com.stela.taskapp.model.Task;

import java.util.ArrayList;


public class TaskRepository {

    private int actualId = 0;
    private static TaskRepository instance;
    private final ArrayList<Task> tasks;
    private String text;

    private TaskRepository() {
        tasks = new ArrayList<>();
    }

    public static synchronized TaskRepository getInstance() {
        if (instance == null) {
            instance = new TaskRepository();
        }
        return instance;
    }

    // Retorna a lista
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Adiciona uma task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Remove uma task
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    // Limpa tudo (opcional)
    public void clear() {
        tasks.clear();
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int createId() {
        actualId += 1;
        return actualId;
    }

}

