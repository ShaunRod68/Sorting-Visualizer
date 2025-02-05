package com.example.sortingvisualizer;

import java.util.ArrayList;

public interface Algo {
    void sort(ArrayList<Integer> array, Controller con); //Abstract Method

    default void sleep() {
        try {
            Thread.sleep(50); // Sleep for 100 milliseconds (or adjust as needed)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
