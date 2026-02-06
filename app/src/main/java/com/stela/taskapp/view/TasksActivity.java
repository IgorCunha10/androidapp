package com.stela.taskapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stela.taskapp.R;
import com.stela.taskapp.model.Category;
import com.stela.taskapp.model.Task;
import com.stela.taskapp.view.adapter.TaskAdapter;
import com.stela.taskapp.data.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {

    private String TAG = "TasksActivity";
    private FloatingActionButton fabNewTask;
    private TaskRepository repo;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private Spinner spnFilterCategory;
    private SearchView searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        CoordinatorLayout mainLayout = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
            int bottomInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
            v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), bottomInset + 24);
            return insets;


        });

        initView();
        initListeners();
        initData();
        initTaskList();

    }

    private void initView() {

        fabNewTask = findViewById(R.id.fab_new_task);
        spnFilterCategory = findViewById(R.id.spnFilterCategory);
        recyclerView = findViewById(R.id.recycler_view);
        searchBar = findViewById(R.id.searchBar);

    }

    private void initData() {
        repo = TaskRepository.getInstance(TasksActivity.this);

        ArrayAdapter<Category> spinnerAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        Category.values()
                );


        spinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spnFilterCategory.setAdapter(spinnerAdapter);

        spnFilterCategory.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {

                        Category selected =
                                (Category) parent.getItemAtPosition(position);

                        List<Task> tasks;

                        if (selected == Category.CHOOSE) {
                            tasks = repo.getAll();
                        } else {
                            tasks = repo.getByCategory(selected);
                        }

                        adapter.setTaskList(tasks);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                }
        );
    }

    private void initTaskList() {

        adapter = new TaskAdapter(
                TasksActivity.this,
                new ArrayList<>(),
                (position, task) -> {
                    repo.removeTask(task);

                }
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                adapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });


    }





    private void initListeners() {

       fabNewTask.setOnClickListener(v -> {
           Intent intent = new Intent(TasksActivity.this, TaskFormActivity.class);
           startActivity(intent);

       });

    }




    @Override
    protected void onResume() {

        super.onResume();

        Category selected =
                (Category) spnFilterCategory.getSelectedItem();

        List<Task> tasks;

        if (selected == Category.CHOOSE) {
            tasks = repo.getAll();
        } else {
            tasks = repo.getByCategory(selected);
        }

        adapter.setTaskList(tasks);
        adapter.notifyDataSetChanged();
    }
}