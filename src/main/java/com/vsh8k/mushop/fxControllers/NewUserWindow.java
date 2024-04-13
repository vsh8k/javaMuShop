package com.vsh8k.mushop.fxControllers;

import com.sun.source.tree.IfTree;
import com.vsh8k.mushop.model.AccountSystem.Hash;
import com.vsh8k.mushop.model.AccountSystem.Manager;
import com.vsh8k.mushop.model.AccountSystem.User;
import com.vsh8k.mushop.model.Database.DBConnector;
import com.vsh8k.mushop.model.Database.UserManager;
import com.vsh8k.mushop.model.Misc.Validate;
import com.vsh8k.mushop.model.Popup.Confirm;
import com.vsh8k.mushop.model.Popup.Information;
import com.vsh8k.mushop.model.Popup.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewUserWindow {
    private DBConnector db;

    @FXML
    TextField fNameField;
    @FXML
    TextField lNameField;
    @FXML
    TextField emailField;
    @FXML
    TextField emailField1;
    @FXML
    TextField passField;
    @FXML
    TextField passField1;
    @FXML
    TextField loginField;
    @FXML
    CheckBox isAdminCheck;

    String login;

    @FXML
    private void addOnClick(){
        try {
            String name = Validate.validateAndConvertString(fNameField.getText(), "First Name");
            String sName = Validate.validateAndConvertString(lNameField.getText(), "Last Name");
            login = Validate.validateAndConvertString(loginField.getText(), "Login");
            String email = Validate.validateEmail(emailField.getText(), "Email");
            String email1 = Validate.validateEquals(email, emailField1.getText(), "Email");
            String pass = Validate.validatePassword(passField.getText(), "Password");
            String pass1 = Validate.validateEquals(pass, passField1.getText(), "Password");
            String passHash = Hash.createHash(pass);
            if(Confirm.display("Create user", "Do you want to add a new user " + login)) {
                int accountLevel = 2;
                if (isAdminCheck.isSelected()) {
                    accountLevel = 1;
                }
                db.connect();
                User newUser = new Manager(0, name, sName, login, passHash, email, accountLevel, isAdminCheck.isSelected());
                UserManager.addUserToDB(db, newUser);
                db.disconnect();
                Information.display("User Created", "Created user " + login);
                Stage stage = (Stage) fNameField.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            Warning.display("Error", e.getMessage());
        }
    }

    public void setDBConnector(DBConnector db) {
        this.db = db;
    }
}
