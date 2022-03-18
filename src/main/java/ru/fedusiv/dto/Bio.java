package ru.fedusiv.dto;

import lombok.*;
import ru.fedusiv.validation.annotations.FieldsNonEquality;
import ru.fedusiv.validation.annotations.ValidAge;
import ru.fedusiv.validation.annotations.ValidInitials;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldsNonEquality(
        message = "{student.NameAndSurnameEquality}",
        field1 = "name",
        field2 = "surname"
)
public class Bio {

    @NotNull
    @ValidInitials(message = "{student.incorrectName}")
    private String name;

    @NotNull
    @ValidInitials(message = "{student.incorrectSurname}")
    private String surname;

    @NotNull
    @ValidAge(message = "{student.incorrectAge}")
    private Integer age;

    @NotNull
    private String group;

}
