package ru.fedusiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.fedusiv.dto.Bio;
import ru.fedusiv.entities.Group;
import ru.fedusiv.entities.Student;
import ru.fedusiv.exceptions.EntitySaveException;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.services.GroupsService;
import ru.fedusiv.services.StudentsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
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
            return group.getStudents();
        } catch (NoEntityException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    @PostMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewStudent(@Valid @RequestBody Bio bio, BindingResult bindingResult)
            throws NoEntityException, EntitySaveException {

        StringBuilder response = new StringBuilder();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors()
                    .forEach(error -> response.append(error.getDefaultMessage()).append("\n"));
            throw new EntitySaveException("Student", response.toString());
        }

        studentsService.save(bio);
        return new ResponseEntity<>("successfully saved", HttpStatus.OK);
    }

    @PostMapping(value = "/students/addALL", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStudents(@RequestBody @Valid List<Bio> biographies)
            throws EntitySaveException, NoEntityException {

        studentsService.saveAll(biographies);
        return new ResponseEntity<>("successfully saved", HttpStatus.OK);
    }
}
