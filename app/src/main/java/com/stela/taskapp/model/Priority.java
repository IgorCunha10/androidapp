package com.stela.taskapp.model;

import androidx.annotation.NonNull;

public enum Priority {
    SELECT(1, "Selecione a prioridade"),
    URGENTE(2, "Urgente"),
    MÉDIA(3, "Média"),
    BAIXA(4, "Baixa");

    private int id;

    private String description;

    Priority (int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Priority fromString(String text) {
        for (Priority s : Priority.values()) {
            if (s.description.equalsIgnoreCase(text)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Nenhuma prioridade encontrado para: " + text);
    }

    public int getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
