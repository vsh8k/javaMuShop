package com.vsh8k.mushop.model.Popup;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Confirm {

    private static final Alert.AlertType alertType = Alert.AlertType.CONFIRMATION;

    public static Boolean display(String title, String message) throws Exception {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        // Cancel button is clicked or dialog is closed
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}