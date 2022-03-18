package ru.fedusiv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedusiv.dto.Bio;
import ru.fedusiv.entities.Group;
import ru.fedusiv.entities.Student;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.exceptions.EntitySaveException;
import ru.fedusiv.repositories.StudentsRepository;
import ru.fedusiv.validation.annotations.StudentsValidator;

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
    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

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
    public List<Student> saveAll(List<Bio> biographies) throws EntitySaveException, NoEntityException {
        List<Student> students = new ArrayList<>();
        for (Bio bio: biographies) {
            students.add(save(bio));
        }
        return students;
    }

    @Override
    public Student getStudentById(Long id) {
        return studentsRepository.findById(id).get();
    }

}
