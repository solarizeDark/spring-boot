package ru.fedusiv.services;

import ru.fedusiv.dto.Bio;
import ru.fedusiv.entities.Student;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.exceptions.SaveException;

import java.util.List;

public interface StudentsService {

    List<Student> findAll();
    Student save(Bio bio) throws NoEntityException, SaveException;
    Student getStudentById(Long id);

}
