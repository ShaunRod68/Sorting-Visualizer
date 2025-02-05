package com.example.sortingvisualizer;

import com.example.sortingvisualizer.Algos.BubbleSort;
import com.example.sortingvisualizer.Algos.HeapSort;
import com.example.sortingvisualizer.Algos.InsertionSort;
import com.example.sortingvisualizer.Algos.QuickSort;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    @FXML
    private HBox barContainer;
    @FXML
    private Slider slider;
    @FXML
    public MenuButton menu;
    public static int arrSize;
    public static double BarWidth;
    int bound = 500;
    private volatile boolean isPaused = false;

    @FXML
    public void initialize(){
        arrSize = (int) slider.getValue();
        updateBarWidth(arrSize);

        slider.setOnMouseReleased(_ ->{
            arrSize = (int) slider.getValue();
            updateBarWidth(arrSize);
        });
    }
    public static ArrayList<Integer> array = new ArrayList<>();
    public static ArrayList<Rectangle> rect= new ArrayList<>();

    @FXML
    protected void GenArray() {
        barContainer.getChildren().clear();
        rect.clear();
        array.clear();
        //updateBarWidth(arrSize);
        randomArray();
        //Animate bars
        Timeline barC = new Timeline();
        for(int i =0;i<arrSize;i++){
            KeyFrame barCreate = new KeyFrame(Duration.millis(i*10), _->{

            Rectangle bar = new Rectangle(BarWidth,5);
            bar.setFill(Color.BLUE);
            barContainer.getChildren().add(bar);
            rect.add(bar);
            });
            barC.getKeyFrames().add(barCreate);
        }

        //PauseTransition delay = new PauseTransition(Duration.seconds(2));


        //Animate bar size
        Timeline barHeight = new Timeline();
        for (int j = 0; j < bound; j++) {

            KeyFrame keyFrame = new KeyFrame(Duration.millis(j * 2), _ -> {
                for (int i = 0; i < rect.size(); i++) {
                    Rectangle bar = rect.get(i);
                    if (bar.getHeight() < array.get(i)) {
                        bar.setHeight(bar.getHeight() + 1);
                    }
                }
            });
            barHeight.getKeyFrames().add(keyFrame);
        }

        SequentialTransition sequence = new SequentialTransition(
                barC,
                barHeight
        );
        sequence.play();
    }
    public synchronized void checkPause(){
        while (isPaused) {
            try{
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    @FXML
    protected void pause() {
        isPaused = true;
    }
    @FXML
    protected void resume() {
        synchronized (this){
            isPaused = false;
            notifyAll();
        }
    }
    @FXML
    protected void updateArray(ActionEvent event){
        MenuItem menuItem = (MenuItem) event.getSource();
        String algo = menuItem.getText();
        if(algo.equalsIgnoreCase("Bubble Sort")){
            BubbleSort bs = new BubbleSort();
            Thread sortingThread = new Thread(() -> bs.sort(array, this), "BubbleSortThread");
            sortingThread.start();
        } else if (algo.equalsIgnoreCase("Insertion Sort")) {
            InsertionSort Is = new InsertionSort();
            Thread sortingThread = new Thread(() -> Is.sort(array, this), "InsertionSortThread");
            sortingThread.start();

        } else if (algo.equalsIgnoreCase("Quick Sort")) {
            QuickSort qs = new QuickSort();
            Thread sortingThread = new Thread(()-> qs.sort(array,this),"QuickSortThread");
            sortingThread.start();
        } else if (algo.equalsIgnoreCase("Heap Sort")) {
            HeapSort hs = new HeapSort();
            Thread sortingThread = new Thread(()-> hs.sort(array,this),"HeapSortThread");
            sortingThread.start();

        }
    }

    public void randomArray(){
        Random rand = new Random();
        for(int i=0;i<arrSize;i++){
            array.add(rand.nextInt(bound)+1);
        }
    }
    private void updateBarWidth(int size){
        switch (size) {
            case 10:
                BarWidth = 90;
                barContainer.setSpacing(10);
                break;
            case 30:
                BarWidth = 32;
                barContainer.setSpacing(2);
                break;
            case 50:
                BarWidth = 19;
                barContainer.setSpacing(1);
                break;
            case 70:
                BarWidth = 13;
                barContainer.setSpacing(0.5);
                break;
            default:
                BarWidth = 8.80;
                barContainer.setSpacing(0.4);
                break;
        }
    }
    public void displayUpdate(int index1,int value){
        checkPause();
        Platform.runLater(()->{
            for(Rectangle rec : rect){
                rec.setFill(Color.BLUE);
            }
            rect.get(index1).setHeight(value);

            rect.get(index1).setFill(Color.RED);

        });
    }
    public void swap(ArrayList<Integer> array, int i, int j, Controller con) {
        con.checkPause();

        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
        con.displayUpdate(i, array.get(i));
        con.displayUpdate(j, array.get(j));
        try {
            Thread.sleep(50); // Sleep for 100 milliseconds (or adjust as needed)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

