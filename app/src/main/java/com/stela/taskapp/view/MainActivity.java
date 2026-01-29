package com.stela.taskapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stela.taskapp.R;
import com.stela.taskapp.model.Priority;
import com.stela.taskapp.model.State;
import com.stela.taskapp.model.Task;
import com.stela.taskapp.util.Mask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnSave;
    private Spinner spnEstado, spnPrioridade;
    private EditText edtData, edtName, edtDescription;
    private State stateSelected;
    private Priority prioritySelected;
    private List<Task> taskList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListeners();
    }


    private void initView() {
        btnSave = findViewById(R.id.btnSave);
        spnEstado = (Spinner) findViewById(R.id.spnState);
        spnPrioridade = (Spinner) findViewById(R.id.spnPrioridade);
        edtData = (EditText) findViewById(R.id.edtData);
        edtName = findViewById(R.id.edtName);
        edtDescription = findViewById(R.id.edtDescription);

        configSpinnerAdapters();

        edtData.addTextChangedListener(Mask.insert("##/##/####", edtData));
    }

    private void configSpinnerAdapters() {

        // State
        ArrayAdapter<State> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                State.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEstado.setAdapter(adapter);

        // Priority
        ArrayAdapter<Priority> adapter1 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Priority.values()
        );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPrioridade.setAdapter(adapter1);

    }

    private void initListeners() {

        spnEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stateSelected = State.fromString(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spnPrioridade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prioritySelected = Priority.fromString(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String description = edtDescription.getText().toString();

            if (name.isBlank() || description.isBlank() || stateSelected == null || prioritySelected == null) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                clearForm();

                Task newTask = new Task(name, description, prioritySelected, stateSelected);
                taskList.add(newTask);
                for (Task task : taskList) {
                    Log.d("LOG_TASKS", task.toString());
                }
                Toast.makeText(this, "Task cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void clearForm() {
        edtData.setText("");
        edtDescription.setText("");

        // TODO colocar opção selecionar nos spinner
    }

}