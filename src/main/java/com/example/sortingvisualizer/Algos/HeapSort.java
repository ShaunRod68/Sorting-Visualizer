package com.example.sortingvisualizer.Algos;

import com.example.sortingvisualizer.Algo;
import com.example.sortingvisualizer.Controller;

import java.util.ArrayList;

public class HeapSort implements Algo {
    @Override
    public void sort(ArrayList<Integer> array, Controller con) {
        heapsort(array,con);
    }
    public void heapsort(ArrayList<Integer> array,Controller con){
        for (int i =(array.size()/2);i>=0;i--){
            maxHeapify(array, array.size(), i,con);
        }
        for(int i = array.size()-1;i>0;i--){
            con.swap(array,0,i,con);
            maxHeapify(array,i,0,con);
        }
    }
    public void maxHeapify(ArrayList<Integer> array,int n,int i,Controller con){
        int largest = i;
        int l = 2*i+1;
        int r= 2*i+2;
        if(l<n && array.get(largest)<array.get(l)){
            largest=l;
        }
        if(r<n && array.get(largest)<array.get(r)){
            largest=r;
        }
        if (largest!=i){
            con.swap(array,largest,i,con);
            maxHeapify(array, n, largest,con);
        }
    }

}
