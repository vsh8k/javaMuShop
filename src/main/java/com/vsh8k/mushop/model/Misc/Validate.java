package com.vsh8k.mushop.model.Misc;

import java.net.URI;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.sql.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vsh8k.mushop.model.Database.DBConnector;
import com.vsh8k.mushop.model.Popup.Warning;
import com.vsh8k.mushop.model.Shop.Cart;
import com.vsh8k.mushop.model.Shop.Media;
import com.vsh8k.mushop.model.Shop.Product;
import javafx.util.Pair;
import lombok.SneakyThrows;

public class Validate {

    public static Integer validateAndConvertInteger(String input, String fieldName) throws ValidationException {
        if (isEmptyString(input)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid integer format in " + fieldName + " field");
        }
    }

    public static Float validateAndConvertFloat(String input, String fieldName) throws ValidationException {
        if (isEmptyString(input)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid float format in " + fieldName + " field");
        }
    }

    public static int validateAndConvertYear(String input, String fieldName) throws ValidationException {
        if (isEmptyString(input)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        int year = Integer.parseInt(input);
        if (year < 1900 || year > Year.now().getValue()) {
            throw new ValidationException("Invalid year format in " + fieldName + " field");
        } else return year;
    }

    public static Time validateAndConvertTime(String input, String fieldName) throws ValidationException {
        if (isEmptyString(input)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        try {
            return Time.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid time format in " + fieldName + " field");
        }
    }

    public static Short validateAndConvertShort(String input, String fieldName) throws ValidationException {
        if (isEmptyString(input)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        try {
            return Short.parseShort(input);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid short format in " + fieldName + " field");
        }
    }

    public static String validateAndConvertString(String input, String fieldName) throws ValidationException {
        if (isEmptyString(input)) {
            throw new ValidationException(fieldName + " field is empty");
        } else {
            return input;
        }
    }

    public static boolean isEmptyString(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static Date validateAndConvertDate(LocalDate date, String fieldName) throws ValidationException {
        System.out.println(date);
        if (date == null) {
            throw new ValidationException(fieldName + " field is empty");
        }
        if (date.isAfter(LocalDate.now().minusYears(14))) {
            throw new ValidationException("You're not old enough to create an account");
        } else return Date.valueOf(date);
    }

    public static String validatePhoneNumber(String number, String fieldName, DBConnector db) throws ValidationException {
        if (isEmptyString(number)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        String regex = "\\+\\d{7,15}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        if (!matcher.find()) {
            throw new ValidationException("Invalid phone number");
        } else {
            try {
                db.connect();
                ResultSet users = db.query("SELECT phone FROM users WHERE phone = '" + number + "'");
                if (users.next()) {
                    db.disconnect();
                    throw new ValidationException("Another user already exists with this number");
                } else {
                    db.disconnect();
                    return number;
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static String validatePassword(String password, String fieldName) throws ValidationException {
        if (isEmptyString(password)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        if (password.length() < 7) {
            throw new ValidationException("Password must be at least 7 characters");
        }
        if (password.length() > 15) {
            throw new ValidationException("I doubt you will remeber that passworrd ;)");
        }
        String regex = "(?=.*[A-Z])(?=.*\\d).+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {
            throw new ValidationException("Password must contain at least one capital letter and a number");
        } else return password;
    }

    public static String validateEmail(String email, String fieldName, DBConnector db) throws ValidationException {
        if (isEmptyString(email)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            throw new ValidationException("Invalid email format in " + fieldName + " field");
        } else {
            try {
                db.connect();
                ResultSet users = db.query("SELECT email FROM users WHERE email = '" + email + "'");
                if (users.next()) {
                    db.disconnect();
                    throw new ValidationException("Another user already exists with this email");
                } else {
                    db.disconnect();
                    return email;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String validateLogin(String login, String fieldName, DBConnector db) throws ValidationException {
        if (isEmptyString(login)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        try {
            db.connect();
            ResultSet users = db.query("SELECT login FROM users WHERE login = '" + login + "'");
            if (users.next()) {
                db.disconnect();
                throw new ValidationException("Another user already exists with this login");
            } else {
                db.disconnect();
                return login;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String validateCreditCardNumber(String ccNumber, String fieldName) throws ValidationException {
        if (isEmptyString(ccNumber)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        String ccRegex = "^[0-9]{16}$";
        Pattern pattern = Pattern.compile(ccRegex);
        Matcher matcher = pattern.matcher(ccNumber);
        if (matcher.matches()) {
            return ccNumber;
        } else {
            throw new ValidationException("Invalid format in " + fieldName + " field");
        }
    }

    public static String validateCVV(String cvv, String fieldName) throws ValidationException {
        if (isEmptyString(cvv)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        String cvvRegex = "^[0-9]{3,4}$";
        Pattern pattern = Pattern.compile(cvvRegex);
        Matcher matcher = pattern.matcher(cvv);
        if (matcher.matches()) {
            return cvv;
        } else {
            throw new ValidationException("Invalid format in " + fieldName + " field");
        }
    }

    public static String validateAndConvertYYMM(String yy, String mm, String fieldName) throws ValidationException {
        if (isEmptyString(yy) || isEmptyString(mm)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        String yymmRegex = "^[0-9]{2}$";
        Pattern pattern = Pattern.compile(yymmRegex);
        Matcher matcher = pattern.matcher(yy);
        Matcher matcher1 = pattern.matcher(mm);
        if (matcher.matches() && matcher1.matches()) {
            return yy + "/" + mm;
        } else {
            throw new ValidationException("Invalid format in " + fieldName + " field");
        }
    }

    public static String validateEquals(String s1, String s2, String fieldName) throws ValidationException {
        if (isEmptyString(s2)) {
            throw new ValidationException(fieldName + " field is empty");
        } else if (!s1.equals(s2)) {
            throw new ValidationException(fieldName + " fields don't match");
        } else {
            return s2;
        }
    }

    @SneakyThrows
    public static void validateCart(Cart cart, DBConnector db) throws ValidationException {
        db.connect();
        for (Pair<Product, Integer> pair : cart.getProductList()) {
            int productId = pair.getKey().getId();
            ResultSet productQtyInDB = db.query("SELECT qty FROM media WHERE id = " + productId);
            if (productQtyInDB.next()) {
                int productQty = productQtyInDB.getInt("qty");
                if(productQty < pair.getValue()) {
                    db.disconnect();
                    throw new ValidationException("Stock of the product " + pair.getKey().toString() + " is less than the requested amount");
                }
            }
        }
        db.disconnect();
    }

    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }
}