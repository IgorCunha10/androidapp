package com.stela.taskapp.model;



public enum State {

    SELECIONE(1, "Escolha o Estado"),
    EM_ANDAMENTO(2, "Em andamento"),
    CONCLUIDO(3, "Concluido"),
    A_FAZER(4, "A fazer");

    private int id;
    private String description;

    State(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static State fromString(String text) {
        for (State s : State.values()) {
            if (s.description.equalsIgnoreCase(text)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Nenhum estado encontrado para: " + text);
    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return description; // O Spinner exibirá esta string
    }
}
