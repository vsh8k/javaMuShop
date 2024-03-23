package com.vsh8k.mushop.fxControllers;

import com.vsh8k.mushop.mainApplication;
import com.vsh8k.mushop.model.AccountSystem.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWindow {
    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink createAccount;

    @FXML
    private TextFlow registerText;

    @FXML
    private TextField unameField;

    @FXML
    private TextField passField;

    @FXML
    private void initialize() {
        createAccount = buildCreateAccountLink();
        registerText.getChildren().addAll(new Text("Don't have an account? "), createAccount);
    }

    @FXML
    private void loginOnClick() {
        System.out.println("LOGIN");
        String token = Login.getToken(unameField.getText(), passField.getText());
        if (!token.equals("401")) {
            System.out.println(token);
            if (true) { //Later on bus DB.getUserToken ar smth like that
                FXMLLoader loader = new FXMLLoader(mainApplication.class.getResource("main-window.fxml"));
                try {
                    Parent root = loader.load();
                    MainWindow mainWindowController = loader.getController();
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(mainApplication.class.getResource("bootstrap3.css").toExternalForm());
                    Stage primaryStage = (Stage) unameField.getScene().getWindow();
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //gavom token'a, validatinam ji, passinam duomenis main controlleriui, incializuojam langa
        }
    }

    private Hyperlink buildCreateAccountLink() {
        Hyperlink createAccount = new Hyperlink("Click here");
        createAccount.setOnAction(actionEvent -> {
            System.out.println("Registracijos mygtukas!");
            FXMLLoader loader = new FXMLLoader(mainApplication.class.getResource("register-window.fxml"));
            try {
                Parent root = loader.load();
                MainWindow mainWindowController = loader.getController();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(mainApplication.class.getResource("bootstrap3.css").toExternalForm());
                Stage primaryStage = (Stage) unameField.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return createAccount;
    }
}
