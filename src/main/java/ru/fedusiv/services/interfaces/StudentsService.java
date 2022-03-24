package ru.fedusiv.services.interfaces;

import ru.fedusiv.dto.Bio;
import ru.fedusiv.dto.StudentDto;
import ru.fedusiv.dto.UpdateStudentDto;
import ru.fedusiv.entities.Student;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.exceptions.EntitySaveException;

import java.util.List;

public interface StudentsService {

    Student save(Bio bio) throws NoEntityException, EntitySaveException;
    Student update(UpdateStudentDto student) throws NoEntityException;
    List<Student> saveAll(List<Bio> biographies) throws NoEntityException, EntitySaveException;
    Student patchStudent(UpdateStudentDto studentDto) throws NoEntityException;
    void delete(Long id) throws NoEntityException;

    // get methods
    StudentDto findByNameAndSurname(String name, String surname) throws NoEntityException;
    List<StudentDto> getAll();
    StudentDto getStudentById(Long id) throws NoEntityException;
    List<StudentDto> getInAgeRange(Integer left, Integer right);
}
