package ru.fedusiv.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import ru.fedusiv.entities.Group;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto extends RepresentationModel<GroupDto>  {

    private String id;
    private LocalDate entranceDate;
    private LocalDate graduateDate;
    private StudentDto monitor;

    public static GroupDto ofBrief (Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .entranceDate(group.getEntranceDate())
                .graduateDate(group.getGraduateDate())
                .build();
    }

    public static GroupDto of (Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .entranceDate(group.getEntranceDate())
                .graduateDate(group.getGraduateDate())
                .monitor(StudentDto.of(group.getMonitor()))
                .build();
    }

    public static void addSelfLink(GroupDto groupDto, Class<?> controller) {
        groupDto.add(linkTo(controller).slash(groupDto.getId()).withSelfRel());
    }

    public static void addLinks(List<GroupDto> groupDtos, Class<?> controller) {
        groupDtos.forEach(groupDto -> addSelfLink(groupDto, controller));
    }

}
