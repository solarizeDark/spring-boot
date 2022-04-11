package ru.fedusiv.services.interfaces;

import ru.fedusiv.entities.Group;
import ru.fedusiv.exceptions.NoEntityException;

public interface GroupsService {

    Group getGroupById(String id) throws NoEntityException;

}
