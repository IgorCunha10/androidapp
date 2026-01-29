package com.stela.taskapp.view;

import static com.stela.taskapp.model.Priority.SELECT;
import static com.stela.taskapp.model.State.SELECIONE;
import android.app.DatePickerDialog;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnSave;
    private Spinner spnEstado, spnPrioridade;
    private EditText edtData, edtName, edtDescription;
    private State stateSelected;
    private Priority prioritySelected;
    private List<Task> taskList = new ArrayList<>();

    Calendar calendar = Calendar.getInstance();

    int ano = calendar.get(Calendar.YEAR);
    int mes = calendar.get(Calendar.MONTH);
    int dia = calendar.get(Calendar.DAY_OF_MONTH);
    // private String date =  edtData.toString();

    ;

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


        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String description = edtDescription.getText().toString();

            if (name.isBlank() || description.isBlank() || stateSelected == SELECIONE || prioritySelected == SELECT) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                clearForm();

                Task newTask = new Task(name, description, prioritySelected, stateSelected, edtData);
                taskList.add(newTask);
                for (Task task : taskList) {
                    Log.d("LOG_TASKS", task.toString());
                }
                Toast.makeText(this, "Task cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
            }
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
        // TODO colocar opção selecionar nos spinner
    }

}