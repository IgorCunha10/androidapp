package com.stela.taskapp.util;

import androidx.room.TypeConverter;

import com.stela.taskapp.model.Priority;
import com.stela.taskapp.model.State;

public class Converters {

    @TypeConverter
    public static String fromPriority(Priority priority) {
        return priority == null ? null : priority.name();
    }

    @TypeConverter
    public static Priority toPriority(String value) {
        return value == null ? null : Priority.valueOf(value);
    }

    @TypeConverter
    public static String fromState(State state) {
        return state == null ? null : state.name();
    }

    @TypeConverter
    public static State toState(String value) {
        return value == null ? null : State.valueOf(value);
    }
}
