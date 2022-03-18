package ru.fedusiv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.fedusiv.dto.Bio;
import ru.fedusiv.entities.Group;
import ru.fedusiv.entities.Student;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.exceptions.SaveException;
import ru.fedusiv.repositories.StudentsRepository;

import java.util.List;

@Service
public class StudentsServiceImpl  implements StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private GroupsService groupsService;

    @Override
    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    @Override
    public Student save(Bio bio) throws NoEntityException, SaveException {
        try {
            Group group = groupsService.getGroupById(bio.getGroup());
            Student student = Student.builder()
                    .name(bio.getName().length() == 0 ? null : bio.getSurname())
                    .surname(bio.getSurname().length() == 0 ? null : bio.getSurname())
                    .age(bio.getAge())
                    .group(group)
                    .build();
            studentsRepository.save(student);
            return student;
        } catch (DataIntegrityViolationException exception) {
            throw new SaveException("bio");
        }
    }

    @Override
    public Student getStudentById(Long id) {
        return studentsRepository.findById(id).get();
    }

}
