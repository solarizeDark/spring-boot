package ru.fedusiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fedusiv.dto.Bio;
import ru.fedusiv.dto.GroupDto;
import ru.fedusiv.dto.StudentDto;
import ru.fedusiv.dto.UpdateStudentDto;
import ru.fedusiv.entities.Group;
import ru.fedusiv.entities.Student;
import ru.fedusiv.exceptions.EntitySaveException;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.services.interfaces.GroupsService;
import ru.fedusiv.services.interfaces.StudentsService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController()
@RequestMapping("/students")
@Validated
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private GroupsService groupsService;

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto getStudentById (@PathVariable("id") Long id) throws NoEntityException {

        Student student = studentsService.getStudentById(id);
        StudentDto studentDto = StudentDto.of(student);
        GroupDto groupDto = studentDto.getGroup();
        Link studentsOfGroupLink =
                linkTo(StudentsController.class).slash("group").slash(groupDto.getId()).withSelfRel();

        GroupDto.addSelfLink(groupDto, GroupsController.class);
        groupDto.add(studentsOfGroupLink);

        return studentDto;
    }

    @GetMapping(value = "/group/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<StudentDto> getAllStudentsByGroup (@PathVariable("id") String groupId) throws NoEntityException {

        Group group = groupsService.getGroupById(groupId);
        List<StudentDto> studentDtos = StudentDto.of(group.getStudents());

        StudentDto.addSelfLinks(studentDtos, StudentsController.class);

        Link groupSelfLink = linkTo(GroupsController.class).slash(group.getId()).withSelfRel();
        return CollectionModel.of(studentDtos, groupSelfLink);
    }

    @GetMapping(value = "/getAll",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDto> getAllStudents() {
        return studentsService.getAll();
    }

    @GetMapping(value = "/age",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDto> getInAgeRange(@RequestParam Integer left, @RequestParam Integer right) {
        List<StudentDto> studentDtos = studentsService.getInAgeRange(left, right);
        StudentDto.addSelfLinks(studentDtos, StudentsController.class);
        return studentDtos;
    }

    @GetMapping(value = "/initials",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto getByNameAndSurname(@RequestParam String name, @RequestParam String surname)
            throws NoEntityException {

        StudentDto studentDto = studentsService.findByNameAndSurname(name, surname);
        StudentDto.addSelfLink(studentDto, StudentsController.class);
        return studentDto;
    }

    @PutMapping(value = "/update",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto updateStudent(@RequestBody UpdateStudentDto student) throws NoEntityException {
        return StudentDto.of(studentsService.update(student));
    }

    @PatchMapping(value = "/patch",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto patchStudent(@RequestBody UpdateStudentDto studentDto) throws NoEntityException {
        return StudentDto.of(studentsService.patchStudent(studentDto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) throws NoEntityException {
        studentsService.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewStudent (@Valid @RequestBody Bio bio, BindingResult bindingResult)
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

    @PostMapping(value = "/addAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStudents (@RequestBody @Valid List<Bio> biographies)
            throws EntitySaveException, NoEntityException {

        studentsService.saveAll(biographies);
        return new ResponseEntity<>("successfully saved", HttpStatus.OK);
    }

}
