package com.vsh8k.mushop;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vsh8k.mushop.model.AccountSystem.Login;
import com.vsh8k.mushop.model.Database.DBConnector;
import com.vsh8k.mushop.model.Popup.Warning;
import com.vsh8k.mushop.model.Shop.Media;
import com.vsh8k.mushop.model.Shop.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class mainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loginFxml = new FXMLLoader(mainApplication.class.getResource("login-window.fxml"));
        Scene scene = new Scene(loginFxml.load());
        Image icon = new Image("https://cdn-icons-png.freepik.com/512/3844/3844724.png");
        stage.getIcons().add(icon);
        stage.setTitle("MuShop - Login");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}