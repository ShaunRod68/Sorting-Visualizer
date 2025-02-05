package com.example.sortingvisualizer.Algos;

import com.example.sortingvisualizer.Algo;
import com.example.sortingvisualizer.Controller;

import java.util.ArrayList;

public class QuickSort implements Algo {

    @Override
    public void sort(ArrayList<Integer> array, Controller con) {
        quickSort(array, 0, array.size() - 1, con);
    }

    private void quickSort(ArrayList<Integer> array, int lb, int ub, Controller con) {
        if (lb < ub) {
            int loc = partition(array, lb, ub, con);
            quickSort(array, lb, loc - 1, con);
            quickSort(array, loc + 1, ub, con);
        }
    }

    private int partition(ArrayList<Integer> array, int lb, int ub, Controller con) {
        int pivot = array.get(lb);
        int start = lb;
        int end = ub;

        while (start < end) {
            while (start < ub && array.get(start) <= pivot) {
                start++;
            }
            while (array.get(end) > pivot) {
                end--;
            }
            if (start < end) {
                con.swap(array, start, end, con);
            }
        }

        con.swap(array, lb, end, con);
        return end;
    }

}
