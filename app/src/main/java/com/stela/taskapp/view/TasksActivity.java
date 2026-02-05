package com.stela.taskapp.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stela.taskapp.R;
import com.stela.taskapp.view.adapter.TaskAdapter;
import com.stela.taskapp.data.TaskRepository;

public class TasksActivity extends AppCompatActivity {

    private String TAG = "TasksActivity";
    private FloatingActionButton fabNewTask;
    private TaskRepository repo;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;


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

    private void initTaskList() {

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new TaskAdapter(TasksActivity.this, repo.getTasks(), (position, task) -> {
            repo.removeTask(task);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private void initView() {

        fabNewTask = findViewById(R.id.fab_new_task);

    }

    private void initListeners() {

       fabNewTask.setOnClickListener(v -> {
           Intent intent = new Intent(TasksActivity.this, TaskFormActivity.class);
           startActivity(intent);
       });

    }

    private void initData() {
        repo = TaskRepository.getInstance(TasksActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (repo != null) {

            adapter.setTaskList(repo.getTasks());
            adapter.notifyDataSetChanged();

        }

    }
}