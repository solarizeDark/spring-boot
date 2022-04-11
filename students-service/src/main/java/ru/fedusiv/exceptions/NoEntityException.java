package ru.fedusiv.exceptions;

public class NoEntityException extends Exception {

    public <T> NoEntityException(String entityName, T id) {
        super(entityName + " with " + id.toString() + " id does not exist");
    }

    public <T> NoEntityException(String entityName, String name, String surname) {
        super(entityName + " with name: " + name + " and surname: " + surname + " does not exist");
    }

}
