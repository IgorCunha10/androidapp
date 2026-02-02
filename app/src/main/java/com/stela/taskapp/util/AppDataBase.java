package com.stela.taskapp.util;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.stela.taskapp.model.Task;
import com.stela.taskapp.util.TaskDAO;
import com.stela.taskapp.view.TasksActivity;

public class AppDataBase {

    @Database(entities = {Task.class}, version = 1, exportSchema = false)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract TaskDAO taskDao();

        private volatile AppDatabase INSTANCE;

        public AppDatabase getInstance(final Context context) {
            if (INSTANCE == null) {
                synchronized (AppDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                        AppDatabase.class, "task_database")
                                .fallbackToDestructiveMigration() // Use this if you don't need complex migrations
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
    }


}
