package ru.fedusiv.exceptions;

public class EntitySaveException extends Exception {

    public EntitySaveException(String entity, String message) {
        super(entity + " save exception\n" + message);
    }

}
