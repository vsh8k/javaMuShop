package com.vsh8k.mushop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Playground extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Sample data
        ObservableList<String> items = FXCollections.observableArrayList(
                "Item 1", "Item 2", "Item 3");

        // Create ListView
        ListView<String> listView = new ListView<>(items);

        // Set custom cell factory
        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new CustomListCell();
            }
        });

        primaryStage.setScene(new Scene(listView, 300, 200));
        primaryStage.show();
    }

    // Custom ListCell implementation
    class CustomListCell extends ListCell<String> {
        private HBox content;
        private Button button;
        private ComboBox<String> comboBox;

        public CustomListCell() {
            super();
            button = new Button("Button");
            comboBox = new ComboBox<>();
            comboBox.getItems().addAll("Option 1", "Option 2", "Option 3");

            content = new HBox(10);
            content.getChildren().addAll(button, comboBox);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item);
                setGraphic(content);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
