package com.stela.taskapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stela.taskapp.R;
import com.stela.taskapp.model.Task;
import com.stela.taskapp.view.TaskFormActivity;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final OnTaskDeleteListener listener;
    private List<Task> taskList;
    private final Context context;

    public TaskAdapter(Context context, List<Task> taskList, OnTaskDeleteListener listener) {
        this.taskList = taskList;
        this.listener = listener;
        this.context = context;
    }

    public void setTaskList(List<Task> tasks) {
        taskList = tasks;
        notifyDataSetChanged();
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
        holder.taskState.setText(task.getState().toString());
        holder.taskCategory.setText(task.getCategory().toString());
        holder.deleteButton.setOnClickListener(v -> {
            deleteItem(holder.getBindingAdapterPosition());
            if (listener != null) {
                listener.onDeleteClick(holder.getBindingAdapterPosition(), task);
            }
        });


        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, TaskFormActivity.class);
            intent.putExtra("task", task);
            intent.putExtra("position", position);
            context.startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskName, taskDescription, taskDate, taskPriority, taskState, taskCategory;
        ImageButton deleteButton, editButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.mainText);
            taskDescription = itemView.findViewById(R.id.taskDescription);
            taskDate = itemView.findViewById(R.id.taskData);
            taskPriority = itemView.findViewById(R.id.taskPriority);
            taskState = itemView.findViewById(R.id.taskState);
            taskCategory = itemView.findViewById(R.id.taskCategory);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton = itemView.findViewById(R.id.editButton);

        }


    }


    public interface OnTaskDeleteListener {
        void onDeleteClick(int position, Task task);

    }


    public void deleteItem(int position) {
        taskList.remove(position);
        notifyItemRemoved(position);
    }


    public interface OnTaskEditListener {
        void onEditClick(int position, Task task);

    }

    public void editItem(int position) {

    }



}


