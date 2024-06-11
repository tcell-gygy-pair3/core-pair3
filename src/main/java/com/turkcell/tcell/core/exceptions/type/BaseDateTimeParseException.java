package com.turkcell.tcell.core.exceptions.type;

public class BaseDateTimeParseException extends java.time.format.DateTimeParseException {


    public BaseDateTimeParseException(String message, CharSequence parsedData, int errorIndex) {
        super(message, parsedData, errorIndex);
    }
}