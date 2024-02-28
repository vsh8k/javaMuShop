package com.vsh8k.mushop.fxControllers;

import com.vsh8k.mushop.model.Customer;
import com.vsh8k.mushop.model.User;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Test {
    public ListView<User> userList;
    public TextField loginField;
    public TextField nameField;
    public PasswordField passwordField;
    public TextField surnameField;

    public void createUser() {

        Customer customer = new Customer(loginField.getText(), passwordField.getText(), nameField.getText(), surnameField.getText());
        //userList.getItems().clear();
        userList.getItems().add(customer);
    }
}
