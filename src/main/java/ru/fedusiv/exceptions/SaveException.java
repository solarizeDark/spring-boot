package ru.fedusiv.exceptions;

public class SaveException extends Exception {

    public SaveException(String message) {
        super("Error while saving: " + message);
    }

}
