package ru.fedusiv.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import ru.fedusiv.entities.Student;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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
                .group(GroupDto.ofBrief(student.getGroup()))
                .build();
    }

    public static List<StudentDto> of (List<Student> students) {
        return students.stream().map(StudentDto::of).collect(Collectors.toList());
    }

    public StudentDto get() {
        return this;
    }

    public static void addSelfLink(StudentDto studentDto, Class<?> controller) {
        studentDto.add(linkTo(controller).slash(studentDto.getId()).withSelfRel());
    }

    public static void addSelfLinks(List<StudentDto> studentDtos, Class<?> controller) {
        studentDtos.forEach(studentDto -> addSelfLink(studentDto, controller));
    }

}
