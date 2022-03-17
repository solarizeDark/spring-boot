package ru.fedusiv.exceptions;

public class NoEntityException extends Exception {

    public NoEntityException(String entityName, String id) {
        super(entityName + " with " + id + " id does not exist");
    }

}
