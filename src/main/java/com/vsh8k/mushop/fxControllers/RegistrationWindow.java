package com.vsh8k.mushop.fxControllers;

import com.vsh8k.mushop.model.AccountSystem.Card;
import com.vsh8k.mushop.model.Misc.Validate;
import com.vsh8k.mushop.model.Popup.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;
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
    private void initialize() {
        // Initialize method, called after all FXML elements have been injected
    }

    @FXML
    private void registerOnClick() {
        try {
            String fName = Validate.validateAndConvertString(fNameField.getText(), "First Name");
            String lName = Validate.validateAndConvertString(lNameField.getText(), "Last Name");
            String number = Validate.validatePhoneNumber(numberField.getText(), "Phone Number");
            Date bDate = Validate.validateAndConvertDate(bDateField.getValue(), "Birth Date");
            String email = Validate.validateEmail(emailField.getText(), "Email");
            String email1 = Validate.validateEquals(emailField.getText(), emailField1.getText(), "Repeat Email");
            String pass = Validate.validatePassword(passField.getText(), "Password");
            String pass1 = Validate.validateEquals(passField.getText(), passField1.getText(), "Repeat Password");
            String cc = Validate.validateCreditCardNumber(ccField.getText(), "Credt Card Number");
            String yymm = Validate.validateAndConvertYYMM(yyField.getText(),  mmField.getText(), "Expiry Date");
            String cvv = Validate.validateCVV(cvvField.getText(), "CVV");
            Card card = new Card(cc, LocalDate.parse(yymm, DateTimeFormatter.ofPattern("YY/MM")), cvv);
            String addr = Validate.validateAndConvertString(addrField.getText(), "Address");
            String city = Validate.validateAndConvertString(cityField.getText(), "City");
            String postcode = Validate.validateAndConvertString(postcodeField.getText(), "Postcode");
            if(!eulaCheck.isSelected()) {
                throw new Validate.ValidationException("To create an Account you need to accept the EULA");
            }
        } catch (Validate.ValidationException e) {
            Warning.display("Error", e.getMessage());
        }

    }

    // Other methods as needed...
}
