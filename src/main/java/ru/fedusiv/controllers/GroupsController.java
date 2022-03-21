package ru.fedusiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.fedusiv.entities.Group;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.services.interfaces.GroupsService;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    @Autowired
    private GroupsService groupsService;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Group getGroupById(@PathVariable("id") String id) throws NoEntityException {
        return groupsService.getGroupById(id);
    }


}
