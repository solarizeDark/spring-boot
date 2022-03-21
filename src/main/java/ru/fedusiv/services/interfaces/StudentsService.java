package ru.fedusiv.services.interfaces;

import ru.fedusiv.dto.Bio;
import ru.fedusiv.entities.Student;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.exceptions.EntitySaveException;

import java.util.List;

public interface StudentsService {

    List<Student> findAll();
    Student save(Bio bio) throws NoEntityException, EntitySaveException;
    Student getStudentById(Long id);
    List<Student> saveAll(List<Bio> biographies) throws NoEntityException, EntitySaveException;

}
