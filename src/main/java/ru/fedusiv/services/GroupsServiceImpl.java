package ru.fedusiv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedusiv.entities.Group;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.repositories.GroupsRepository;

@Service
public class GroupsServiceImpl implements GroupsService {

    @Autowired
    private GroupsRepository groupsRepository;

    @Override
    public Group getGroupById(String id) throws NoEntityException {
        return groupsRepository.findById(id).orElseThrow(() -> new NoEntityException("Group", id));
    }
}
