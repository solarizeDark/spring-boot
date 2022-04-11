package ru.fedusiv.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import ru.fedusiv.dto.GroupDto;
import ru.fedusiv.dto.StudentDto;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.services.interfaces.GroupsService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    @Autowired
    private GroupsService groupsService;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public GroupDto getGroupById(@PathVariable("id") String id) throws NoEntityException {
        GroupDto group = GroupDto.of(groupsService.getGroupById(id));
        StudentDto monitor = group.getMonitor().get();
        monitor.setGroup(null);
        Link monitorSelf = linkTo(StudentsController.class).slash(monitor.getId()).withSelfRel();
        monitor.add(monitorSelf);

        return group;
    }

}
