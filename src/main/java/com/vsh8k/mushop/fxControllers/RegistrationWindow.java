package com.vsh8k.mushop.fxControllers;

import com.vsh8k.mushop.model.AccountSystem.Card;
import com.vsh8k.mushop.model.AccountSystem.Customer;
import com.vsh8k.mushop.model.AccountSystem.Hash;
import com.vsh8k.mushop.model.AccountSystem.User;
import com.vsh8k.mushop.model.Database.DBConnector;
import com.vsh8k.mushop.model.Database.UserManager;
import com.vsh8k.mushop.model.Misc.Validate;
import com.vsh8k.mushop.model.Popup.Information;
import com.vsh8k.mushop.model.Popup.Warning;
import com.vsh8k.mushop.model.Shop.CreditCard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.hibernate.annotations.processing.SQL;
import org.hibernate.event.internal.AbstractReassociateEventListener;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistrationWindow {

    @FXML
    private TextField fNameField;

    @FXML
    private TextField lNameField;

    @FXML
    private TextField numberField;

    @FXML
    private DatePicker bDateField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField emailField1;

    @FXML
    private TextField passField;

    @FXML
    private TextField passField1;

    @FXML
    private TextField ccField;

    @FXML
    private TextField yyField;

    @FXML
    private TextField mmField;

    @FXML
    private TextField cvvField;

    @FXML
    private TextField postcodeField;

    @FXML
    private TextField cityField;

    @FXML
    private CheckBox eulaCheck;

    @FXML
    private Button registerButton;

    @FXML
    private TextField addrField;

    @FXML
    private Text cardType;

    @FXML
    private void initialize() {
        // Initialize method, called after all FXML elements have been injected
    }

    private DBConnector db;

    @FXML
    private void registerOnClick(){
        try {
            String fName = Validate.validateAndConvertString(fNameField.getText(), "First Name");
            String lName = Validate.validateAndConvertString(lNameField.getText(), "Last Name");
            String number = Validate.validatePhoneNumber(numberField.getText(), "Phone Number", db);
            Date bDate = Validate.validateAndConvertDate(bDateField.getValue(), "Birth Date");
            String email = Validate.validateEmail(emailField.getText(), "Email", db);
            String email1 = Validate.validateEquals(emailField.getText(), emailField1.getText(), "Repeat Email");
            String pass = Validate.validatePassword(passField.getText(), "Password");
            String pass1 = Validate.validateEquals(passField.getText(), passField1.getText(), "Repeat Password");
            String cc = Validate.validateCreditCardNumber(ccField.getText(), "Credt Card Number");
            String yymm = Validate.validateAndConvertYYMM(yyField.getText(),  mmField.getText(), "Expiry Date");
            String cvv = Validate.validateCVV(cvvField.getText(), "CVV");
            CreditCard card = new CreditCard(cc, fName + " " + lName , yymm, cvv);
            String addr = Validate.validateAndConvertString(addrField.getText(), "Address");
            String city = Validate.validateAndConvertString(cityField.getText(), "City");
            String postcode = Validate.validateAndConvertString(postcodeField.getText(), "Postcode");
            String login = Validate.validateLogin("none", "Login", db);//FEP
            String addreString = addr + ":" + city + ":" + postcode;
            if(!eulaCheck.isSelected()) {
                throw new Validate.ValidationException("To create an Account you need to accept the EULA");
            }
            User user = new Customer(0, fName, lName, login, email, number, Hash.createHash(pass), 3, card, null, addreString, bDateField.getValue());
            try {
                UserManager.addUserToDB(db, user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Information.display("Success", "Your account has been successfully created");
        } catch (Validate.ValidationException e) {
            Warning.display("Error", e.getMessage());
        }

    }

    @FXML
    private void updateCardType() {
        try {
            switch (ccField.getText().charAt(0)) {
                case '3':
                    cardType.setText("Amex");
                    break;
                case '4':
                    cardType.setText("Visa");
                    break;
                case '5':
                    cardType.setText("MC");
                    break;
                case '6':
                    cardType.setText("Discover");
                    break;
            }
        } catch (Exception e) {

        }
    }

    public void setDBConnector(DBConnector db) {
        this.db = db;
    }
    // Other methods as needed...
}
