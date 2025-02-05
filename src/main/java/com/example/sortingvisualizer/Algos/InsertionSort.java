package com.example.sortingvisualizer.Algos;

import com.example.sortingvisualizer.Algo;
import com.example.sortingvisualizer.Controller;

import java.util.ArrayList;

public class InsertionSort implements Algo {
    @Override
    public void sort(ArrayList<Integer> array, Controller con){
        for(int i =1;i<array.size();i++){
            int j=i-1;
            int x=array.get(i);
            while(j>-1 && array.get(j)>x){
                array.set(j + 1, array.get(j));
                con.displayUpdate(j+1,array.get(j));
                sleep();
                j--;
            }
            array.set(j + 1, x);
            con.displayUpdate(j+1,x);
            sleep();
        }
    }
}
