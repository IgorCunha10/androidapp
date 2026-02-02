package com.stela.taskapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stela.taskapp.R;
import com.stela.taskapp.model.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {



    private ArrayList<Task> taskList;

    public TaskAdapter(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new TaskViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        Task task = taskList.get(position);

        holder.taskName.setText(task.getName());
        holder.taskDescription.setText(task.getDescription());
        holder.taskDate.setText(task.getDate());
        holder.taskPriority.setText(task.getPriority().toString());
        holder.taskState.setText(task.getState().toString());;

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskName, taskDescription, taskDate, taskPriority, taskState;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.mainText);
            taskDescription = itemView.findViewById(R.id.taskDescription);
            taskDate = itemView.findViewById(R.id.taskData);
            taskPriority = itemView.findViewById(R.id.taskPriority);
            taskState = itemView.findViewById(R.id.taskState);
        }
    }
}

