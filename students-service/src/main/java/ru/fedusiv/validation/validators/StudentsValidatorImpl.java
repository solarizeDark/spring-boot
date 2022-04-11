package ru.fedusiv.validation.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fedusiv.dto.Bio;
import ru.fedusiv.exceptions.EntitySaveException;
import ru.fedusiv.repositories.StudentsRepository;
import ru.fedusiv.validation.annotations.StudentsValidator;

@Component
public class StudentsValidatorImpl implements StudentsValidator {

    @Autowired
    private StudentsRepository studentsRepository;

    public void checkStudent(Bio bio) throws EntitySaveException {

        if (studentsRepository.findByNameAndSurname(bio.getName(), bio.getSurname()).isPresent()) {
            throw new EntitySaveException("Student", "Error: student with such name and surname already exists!");
        }
    }

}
