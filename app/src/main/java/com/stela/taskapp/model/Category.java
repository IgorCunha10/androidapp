package com.stela.taskapp.model;

import androidx.annotation.NonNull;

public enum Category {
    CHOOSE(1, "Escolha a Categoria"),
    CASA(2, "Casa"),
    FACULDADE(3, "Faculdade"),
    TRABALHO(4, "Trabalho");

    private int id;

    private String description;

    Category(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Category fromString(String text) {
        for (Category s : Category.values()) {
            if (s.description.equalsIgnoreCase(text)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para: " + text);
    }

    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    @NonNull
    @Override
    public String toString() {
        return description;
    }

}
