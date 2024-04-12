package com.vsh8k.mushop.model.Popup;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

public class Repeat extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static String repeatFieldValue(String fieldName) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setContentText("Please repeat the value for the " + fieldName + " field:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    @Override
    public void start(Stage primaryStage) {
        // This method is not used in this minimal example
    }
}
