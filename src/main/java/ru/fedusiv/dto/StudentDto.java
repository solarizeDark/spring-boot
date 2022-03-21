package ru.fedusiv.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import ru.fedusiv.entities.Student;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto extends RepresentationModel<StudentDto> {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private GroupDto group;

    public static StudentDto of (Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .age(student.getAge())
                .name(student.getName())
                .surname(student.getSurname())
                .group(GroupDto.of(student.getGroup()))
                .build();
    }

}
