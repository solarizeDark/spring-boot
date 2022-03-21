package ru.fedusiv.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import ru.fedusiv.entities.Group;
import ru.fedusiv.entities.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto extends RepresentationModel<GroupDto>  {

    private String id;
    private LocalDate entranceDate;
    private LocalDate graduateDate;

    public static GroupDto of (Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .entranceDate(group.getEntranceDate())
                .graduateDate(group.getGraduateDate())
                .build();
    }

}
