package ru.fedusiv.validation.annotations;

import ru.fedusiv.dto.Bio;
import ru.fedusiv.exceptions.EntitySaveException;

public interface StudentsValidator {

    void checkStudent(Bio bio) throws EntitySaveException;

}
