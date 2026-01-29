package com.stela.taskapp.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private String name;
    private String description;
    private Priority priority;
    private State state;

    public Task(String name, String description, Priority priority, State state) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.state = state;
    }

    @NonNull
    @Override
    public String toString() {
        return "Task{" +
                "name='" + this.name + '\'' +
                ", description='" + this.description + '\'' +
                ", priority=" + this.priority +
                ", state=" + this.state +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
