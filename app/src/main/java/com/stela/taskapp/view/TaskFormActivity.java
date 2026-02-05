package com.stela.taskapp.view;

import static com.stela.taskapp.model.Priority.SELECT;
import static com.stela.taskapp.model.State.SELECIONE;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.stela.taskapp.R;
import com.stela.taskapp.data.TaskRepository;
import com.stela.taskapp.model.Category;
import com.stela.taskapp.model.Priority;
import com.stela.taskapp.model.State;
import com.stela.taskapp.model.Task;

import java.util.Calendar;
import java.util.Locale;

public class TaskFormActivity extends AppCompatActivity {

    private Task actualTask;
    private Button btnSave;
    private Spinner spnEstado, spnPrioridade, spnCategory;
    private EditText edtData, edtName, edtDescription;
    private TextView tvMainScText;
    private State stateSelected;
    private Priority prioritySelected;

    private Category categorySelected;
    private TaskRepository repo;
    private Boolean isEdit = false;

    private int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListeners();
        initData();

        Intent intent = getIntent();
        actualTask = (Task) intent.getSerializableExtra("task");


        if (intent.hasExtra("task")){

            isEdit = true;
            actualTask = (Task) intent.getSerializableExtra("task");
            position = intent.getIntExtra("position", -1);

            carregarDados();
            configurarTelaEdicao();
        }



    }

    private void carregarDados() {
        edtName.setText(actualTask.getName());
        edtDescription.setText(actualTask.getDescription());
        edtData.setText(actualTask.getDate());
        spnPrioridade.setSelection(actualTask.getPriority().ordinal());
        spnEstado.setSelection(actualTask.getState().ordinal());
        spnCategory.setSelection(actualTask.getCategory().ordinal());

    }

    private void configurarTelaEdicao() {
        tvMainScText.setText("Editar Tarefa");
        btnSave.setText("Salvar Alterações");

    }


    private void initData() {
        repo = TaskRepository.getInstance(TaskFormActivity.this);
    }


    private void initView() {
        btnSave = findViewById(R.id.btnSave);
        spnEstado = (Spinner) findViewById(R.id.spnState);
        spnPrioridade = (Spinner) findViewById(R.id.spnPrioridade);
        edtData = (EditText) findViewById(R.id.edtData);
        edtName = findViewById(R.id.edtName);
        edtDescription = findViewById(R.id.edtDescription);
        tvMainScText = findViewById(R.id.tvMainScText);
        spnCategory = findViewById(R.id.spnCategory);

        configSpinnerAdapters();
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

        //Category
        ArrayAdapter<Category> adapter2 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Category.values()
        );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategory.setAdapter(adapter2);

    }


    private void initListeners() {

        edtData.setOnClickListener(v -> abrirDatePicker());

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

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelected = Category.fromString(parent.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        btnSave.setOnClickListener(v -> {

            String name = edtName.getText().toString();
            String description = edtDescription.getText().toString();

            if (name.isBlank() || description.isBlank()
                    || stateSelected == SELECIONE
                    || prioritySelected == SELECT
                    || categorySelected == Category.CHOOSE) {

                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            String date = edtData.getText().toString();

            if (isEdit) {

                actualTask.setName(name);
                actualTask.setDescription(description);
                actualTask.setPriority(prioritySelected);
                actualTask.setState(stateSelected);
                actualTask.setCategory(categorySelected);
                actualTask.setDate(date);

                repo.updateTask(actualTask);
                Toast.makeText(this, "Tarefa Atualizada com sucesso!", Toast.LENGTH_SHORT).show();


            } else {

                Task newTask = new Task(
                        name,
                        description,
                        prioritySelected,
                        stateSelected,
                        categorySelected,
                        date
                );
                repo.addTask(newTask);
            }

            clearForm();
            finish();
        });


    }

    private void abrirDatePicker() {

        Calendar hoje = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {

                    String dataFormatada = String.format(
                            Locale.getDefault(),
                            "%02d/%02d/%04d",
                            dayOfMonth, month + 1, year
                    );

                    edtData.setText(dataFormatada);
                },
                hoje.get(Calendar.YEAR),
                hoje.get(Calendar.MONTH),
                hoje.get(Calendar.DAY_OF_MONTH)
        );

        // 🔒 DATA MÍNIMA = HOJE
        datePickerDialog.getDatePicker()
                .setMinDate(hoje.getTimeInMillis());

        // (opcional) data máxima
        Calendar dataMaxima = Calendar.getInstance();
        dataMaxima.set(2100, Calendar.DECEMBER, 31);

        datePickerDialog.getDatePicker()
                .setMaxDate(dataMaxima.getTimeInMillis());

        datePickerDialog.show();
    }


    private void clearForm() {
        edtData.setText("");
        edtDescription.setText("");
        edtName.setText("");

    }



    }

