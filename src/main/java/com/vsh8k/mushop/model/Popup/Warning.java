package com.vsh8k.mushop.model.Popup;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Warning {

    private static Alert.AlertType alertType = Alert.AlertType.WARNING;

    public static void display(String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
