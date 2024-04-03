package com.vsh8k.mushop.model.Misc;

import java.time.DateTimeException;
import java.time.Year;
import java.sql.Time;
import com.vsh8k.mushop.model.Popup.Warning;

public class Validate {

    public static Integer validateAndConvertInteger(String input, String fieldName) {
        if (isEmptyString(input)) {
            Warning.display("Validation Error", fieldName + " field is empty");
            return null;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Warning.display("Validation Error", "Invalid integer format in " + fieldName + " field");
            return null;
        }
    }

    public static Float validateAndConvertFloat(String input, String fieldName) {
        if (isEmptyString(input)) {
            Warning.display("Validation Error", fieldName + " field is empty");
            return null;
        }
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            Warning.display("Validation Error", "Invalid float format in " + fieldName + " field");
            return null;
        }
    }

    public static Year validateAndConvertYear(String input, String fieldName) {
        if (isEmptyString(input)) {
            Warning.display("Validation Error", fieldName + " field is empty");
            return null;
        }
        try {
            return Year.parse(input);
        } catch (DateTimeException e) {
            Warning.display("Validation Error", "Invalid year format in " + fieldName + " field");
            return null;
        }
    }

    public static Time validateAndConvertTime(String input, String fieldName) {
        if (isEmptyString(input)) {
            Warning.display("Validation Error", fieldName + " field is empty");
            return null;
        }
        try {
            return Time.valueOf(input);
        } catch (IllegalArgumentException e) {
            Warning.display("Validation Error", "Invalid time format in " + fieldName + " field");
            return null;
        }
    }

    public static Short validateAndConvertShort(String input, String fieldName) {
        if (isEmptyString(input)) {
            Warning.display("Validation Error", fieldName + " field is empty");
            return null;
        }
        try {
            return Short.parseShort(input);
        } catch (NumberFormatException e) {
            Warning.display("Validation Error", "Invalid short format in " + fieldName + " field");
            return null;
        }
    }

    public static String validateAndConvertString(String input, String fieldName) {
        if (isEmptyString(input)) {
            Warning.display("Validation Error", fieldName + " field is empty");
            return null;
        } else {
            return input;

        }
    }
    public static boolean isEmptyString(String input) {
        return input == null || input.trim().isEmpty();
    }
}
