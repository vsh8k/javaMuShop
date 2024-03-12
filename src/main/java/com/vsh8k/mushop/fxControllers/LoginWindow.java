package com.vsh8k.mushop.fxControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginWindow {
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
     private TextField unameField;
    @FXML
    private TextField passField;

    @FXML
    private void loginOnClick() {
        System.out.println("LOGIN");
    }
    @FXML
    private void registerOnClick() {
        System.out.println("REGISTER");
    }
}
