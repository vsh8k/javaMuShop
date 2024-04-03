package com.vsh8k.mushop.model.Popup;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Warning {

    public static void display(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
