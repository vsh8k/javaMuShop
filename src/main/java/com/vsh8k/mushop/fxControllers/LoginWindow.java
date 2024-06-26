package com.vsh8k.mushop.fxControllers;

import com.vsh8k.mushop.mainApplication;
import com.vsh8k.mushop.model.AccountSystem.Hash;
import com.vsh8k.mushop.model.AccountSystem.Login;
import com.vsh8k.mushop.model.AccountSystem.User;
import com.vsh8k.mushop.model.Database.DBConnector;
import com.vsh8k.mushop.model.Popup.Warning;
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

    DBConnector loginConnector;

    @FXML
    private void initialize() {
        String dbUrl = "jdbc:mysql://localhost:3306/products";
        String dbUsername = "root";
        String dbPassword = "";
        loginConnector = new DBConnector(dbUrl, dbUsername, dbPassword);
        createAccount = buildCreateAccountLink();
        registerText.getChildren().addAll(new Text("Don't have an account? "), createAccount);
    }

    @FXML
    private void loginOnClick() {
        System.out.println("LOGIN");
        User user = null;
        try {
            user = Login.validateAndGetUser(unameField.getText(), passField.getText(), loginConnector);
            FXMLLoader loader = new FXMLLoader(mainApplication.class.getResource("main-window.fxml"));
            try {
                Parent root = loader.load();
                MainWindow mainWindowController = loader.getController();
                mainWindowController.setDBConnector(loginConnector);
                System.out.println(user);
                mainWindowController.setUser(user);
                Scene scene = new Scene(root);
                Stage primaryStage = (Stage) unameField.getScene().getWindow();
                mainWindowController.setPrimaryStage(primaryStage);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            //Warning.display("Error", e.getMessage());
            e.printStackTrace();
        }
    }

    private Hyperlink buildCreateAccountLink() {
        Hyperlink createAccount = new Hyperlink("Click here");
        createAccount.setOnAction(actionEvent -> {
            System.out.println("Registracijos mygtukas!");
            FXMLLoader loader = new FXMLLoader(mainApplication.class.getResource("registration-window.fxml"));
            try {
                Parent root = loader.load();
                RegistrationWindow registrationWindowController = loader.getController();
                registrationWindowController.setDBConnector(loginConnector);
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("MuShop v0.1 - Registration");
                primaryStage.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return createAccount;
    }
}
