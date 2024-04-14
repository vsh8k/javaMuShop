package com.vsh8k.mushop;

import java.io.IOException;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class mainApplication extends Application {

    public static String getVersion() {
        return "v0.1";
    }

    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        FXMLLoader loginFxml = new FXMLLoader(mainApplication.class.getResource("login-window.fxml"));
        Scene scene = new Scene(loginFxml.load());
        Image icon = new Image("https://cdn-icons-png.freepik.com/512/3844/3844724.png");
        stage.getIcons().add(icon);
        stage.setTitle("MuShop " + getVersion() + " - Login");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}