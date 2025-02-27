package com.ias101.lab1.security;



public class InputSanitizer {
    public static String escapeInput(String input) {
        if (input != null) {
            return input.replace("'", "")  // it replaces single quotes into blank or removed
                    .replace("\"", "")  // also removes double quotes
                    .replace(";", "")  // Remove semicolons
                    .replace("--", "") // Remove comment injection
                    .replace("OR","") // Remove OR in every username and password
                    .replace("=", ""); //remove equals sign to prevent from
        }
        return null;
    }


}