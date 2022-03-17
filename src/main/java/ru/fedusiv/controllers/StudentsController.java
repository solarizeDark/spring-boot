package ru.fedusiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.fedusiv.entities.Group;
import ru.fedusiv.entities.Student;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.services.GroupsService;
import ru.fedusiv.services.StudentsService;

import java.util.List;

@RestController
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private GroupsService groupsService;

    @GetMapping(value = "/students",
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "studentId")
    @ResponseBody
    public Student getStudentByIdGET(@RequestParam("studentId") Long studentId) {
        return studentsService.getStudentById(studentId);
    }

    @GetMapping(value = "/students")
    public ModelAndView studentsGET() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("students");
        mav.addObject("students", studentsService.findAll());
        return mav;
    }

    @GetMapping(value = "/students",
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "groupId")
    @ResponseBody
    public List<Student> getAllStudentsByGroup (
            @RequestParam(value = "groupId") String groupId) {
        try {
            Group group = groupsService.getGroupById(groupId);
            List<Student> students = group.getStudents();
            return group.getStudents();
        } catch (NoEntityException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

}
