package com.vsh8k.mushop.model.Misc;

import java.time.DateTimeException;
import java.time.Year;
import java.sql.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vsh8k.mushop.model.Popup.Warning;

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

    public static Year validateAndConvertYear(String input, String fieldName) throws ValidationException {
        if (isEmptyString(input)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        try {
            return Year.parse(input);
        } catch (DateTimeException e) {
            throw new ValidationException("Invalid year format in " + fieldName + " field");
        }
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

    public static String validateEmail(String email, String fieldName) throws ValidationException {
        if (isEmptyString(email)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()) {
            return email;
        }
        else {
            throw new ValidationException("Invalid email format in " + fieldName + " field");
        }
    }

    public static String validateCreditCardNumber(String ccNumber, String fieldName) throws ValidationException {
        if (isEmptyString(ccNumber)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        String ccRegex = "^[0-9]{16}$";
        Pattern pattern = Pattern.compile(ccRegex);
        Matcher matcher = pattern.matcher(ccNumber);
        if(matcher.matches()) {
            return ccNumber;
        }
        else {
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
        if(matcher.matches()) {
            return cvv;
        }
        else {
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
        if(matcher.matches() && matcher1.matches()) {
            return yy + "/" + mm;
        }
        else {
            throw new ValidationException("Invalid format in " + fieldName + " field");
        }
    }

    public static String validateEquals(String s1, String s2, String fieldName) throws ValidationException {
        if(isEmptyString(s2)) {
            throw new ValidationException(fieldName + " field is empty");
        }
        else if (!s1.equals(s2)) {
            throw new ValidationException(fieldName + " fields don't match");
        }
        else {
            return s2;
        }
    }

    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }
}