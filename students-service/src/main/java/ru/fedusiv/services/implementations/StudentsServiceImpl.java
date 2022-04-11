package ru.fedusiv.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedusiv.dto.Bio;
import ru.fedusiv.dto.StudentDto;
import ru.fedusiv.dto.UpdateStudentDto;
import ru.fedusiv.entities.Group;
import ru.fedusiv.entities.Student;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.exceptions.EntitySaveException;
import ru.fedusiv.repositories.StudentsRepository;
import ru.fedusiv.services.interfaces.GroupsService;
import ru.fedusiv.services.interfaces.StudentsService;
import ru.fedusiv.validation.annotations.StudentsValidator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsServiceImpl  implements StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private StudentsValidator studentsValidator;

    @Override
    public Student save(Bio bio) throws NoEntityException, EntitySaveException {
        Group group = groupsService.getGroupById(bio.getGroup());
        studentsValidator.checkStudent(bio);
        Student student = Student.builder()
                .name(bio.getName())
                .surname(bio.getSurname())
                .age(bio.getAge())
                .group(group)
                .build();
        studentsRepository.save(student);
        return student;
    }

    @Override
    public Student update(UpdateStudentDto student) throws NoEntityException {
        if (!studentsRepository.exists(student.getId()))
            throw new NoEntityException("Student", student.getId());

        Group group = groupsService.getGroupById(student.getGroup());

        Student newStudent =
                Student.builder()
                        .id(student.getId())
                        .age(student.getAge())
                        .group(group)
                        .name(student.getName())
                        .surname(student.getSurname())
                        .build();

        studentsRepository.save(newStudent);
        return newStudent;
    }

    @Override
    public List<Student> saveAll(List<Bio> biographies) throws EntitySaveException, NoEntityException {
        List<Student> students = new ArrayList<>();
        for (Bio bio: biographies) {
            students.add(save(bio));
        }
        return students;
    }

    @Override
    public StudentDto getStudentById(Long id) throws NoEntityException {
        return StudentDto.of(
                studentsRepository
                        .findById(id)
                        .orElseThrow(() -> new NoEntityException("Student", id)
                )
        );
    }

    @Override
    public List<StudentDto> getAll() {
        return StudentDto.of(studentsRepository.findAll());
    }

    @Override
    public List<StudentDto> getInAgeRange(Integer left, Integer right) {
        return StudentDto.of(studentsRepository.findInAgeRange(left, right));
    }

    @Override
    public StudentDto findByNameAndSurname(String name, String surname) throws NoEntityException {
        return StudentDto.of(
                studentsRepository
                        .findByNameAndSurname(name, surname)
                        .orElseThrow(() -> new NoEntityException("Student", name, surname))
        );
    }

    public Student getStudentByIdService(Long id) throws NoEntityException {
        return studentsRepository.findById(id).orElseThrow(() -> new NoEntityException("Student", id));
    }

    @Override
    public Student patchStudent(UpdateStudentDto studentDto) throws NoEntityException {
        Student student = getStudentByIdService(studentDto.getId());
        for (Field field : UpdateStudentDto.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(studentDto) != null) {
                    if (field.getName().equals("group")) {
                        Group group = groupsService.getGroupById(studentDto.getGroup());
                        student.setGroup(group);
                    } else {
                        Field toUpdate = Student.class.getDeclaredField(field.getName());
                        toUpdate.setAccessible(true);
                        toUpdate.set(student, field.get(studentDto));
                    }
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return studentsRepository.save(student);
    }

    @Override
    public void delete(Long id) throws NoEntityException {
        if (!studentsRepository.exists(id))
            throw new NoEntityException("Student", id);
        studentsRepository.delete(id);
    }
}
