package com.vsh8k.mushop.model.Misc;

import java.time.DateTimeException;
import java.time.Year;
import java.sql.Time;
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

    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }
}