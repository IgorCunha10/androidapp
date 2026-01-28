package com.stela.taskapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Object spnPrioridade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button taskButton = findViewById(R.id.button);

        Spinner spnEstado = (Spinner) findViewById(R.id.spnState);
        ArrayAdapter<State> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, // Layout do item selecionado
                State.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEstado.setAdapter(adapter);

        Spinner spnPrioridade = (Spinner) findViewById(R.id.spnPrioridade);
        ArrayAdapter<Priority> adapter1 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, // Layout do item selecionado
                Priority.values()
        );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPrioridade.setAdapter(adapter1);


//        taskButton.setOnClickListener(v -> {
//            taskList.add(findViewById((Spinner) spnEstado));
//            taskList.add(findViewById((Spinner) spnPrioridade));
//
//            }

//    }

    }
//
}