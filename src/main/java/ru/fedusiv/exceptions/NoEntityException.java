package ru.fedusiv.exceptions;

public class NoEntityException extends Exception {

    public <T> NoEntityException(String entityName, T id) {
        super(entityName + " with " + id.toString() + " id does not exist");
    }

}
