package com.stela.taskapp;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public enum Priority {
    URGENTE(1, "URGENTE"), MÉDIA(2, "Média"),
    BAIXA(3, "Baixa");

    private int id;

    private String description;

    Priority (int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return description;
    }
}
