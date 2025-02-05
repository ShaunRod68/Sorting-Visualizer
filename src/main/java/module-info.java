module com.example.sortingvisualizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sortingvisualizer to javafx.fxml;
    exports com.example.sortingvisualizer;
}