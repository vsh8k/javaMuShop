package com.vsh8k.mushop;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loginFxml = new FXMLLoader(mainApplication.class.getResource("login-window.fxml"));
        Scene scene = new Scene(loginFxml.load());
        stage.setTitle("MuShop - Login");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}