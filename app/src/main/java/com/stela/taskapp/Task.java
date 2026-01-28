package com.stela.taskapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task {
    public static class DataHolder {
        private static DataHolder instance;
        private List<String> sharedData = new ArrayList<>();

        public static DataHolder getInstance() {
            if (instance == null) {
                instance = new DataHolder();
            }
            return instance;
        }

        public List<String> getSharedData() {
            return sharedData;
        }
    }
// Access from any activity: DataHolder.getInstance().getSharedData().add("New data");

}
