package com.vsh8k.mushop.fxControllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainWindow {
    @FXML
    protected TextField artistField;
    @FXML
    protected void updateButtonOnClick() {
        System.out.println(artistField.getText());
    }
}
