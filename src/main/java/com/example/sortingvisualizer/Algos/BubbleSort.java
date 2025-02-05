package com.example.sortingvisualizer.Algos;

import com.example.sortingvisualizer.Controller;
import com.example.sortingvisualizer.Algo;

import java.util.ArrayList;

public class BubbleSort implements Algo {
    public void sort(ArrayList<Integer> array, Controller con){
        for(int i =0;i< array.size()-1;i++){
            for(int j=0;j< array.size()-i-1;j++){
                if (array.get(j)>array.get(j+1)){
                    //swap
                    int temp=array.get(j);
                    array.set(j,array.get(j+1));
                    array.set(j+1,temp);

                    con.displayUpdate(j,array.get(j));
                    con.displayUpdate(j+1,array.get(j+1));
                    sleep();
                }
            }
        }
    }

}
