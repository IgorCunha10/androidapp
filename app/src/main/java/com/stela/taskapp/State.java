package com.stela.taskapp;

import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public enum State {
    EM_ANDAMENTO(1, "Em andamento"),
    CONCLUIDO(2, "Concluido"),
    A_FAZER(3, "A fazer");

    private int id;
    private String desciption;

    State(int id, String desciption) {
        this.id = id;
        this.desciption = desciption;
    }

    public int getId() {
        return id;
    }

    public String getDesciption() {
        return desciption;
    }


    @Override
    public String toString() {
        return desciption; // O Spinner exibirá esta string
    }
}
