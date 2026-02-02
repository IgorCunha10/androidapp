package com.stela.taskapp.model;
import android.widget.EditText;
import androidx.annotation.NonNull;

import java.io.Serializable;

public class Task implements Serializable {

    private String name;
    private String description;
    private Priority priority;
    private State state;

    private String date;

    public Task(String name, String description, Priority priority, State state, String date) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.state = state;
        this.date = date;
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

    public String getDescription() {
        return description;
    }


    public Priority getPriority() {
        return priority;
    }

    public State getState() {
        return state;
    }


    public String getDate() {
        return date;
    }

}
