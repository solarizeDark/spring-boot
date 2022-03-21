package ru.fedusiv.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import ru.fedusiv.json.GroupJsonSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
//@JsonSerialize(using = GroupJsonSerializer.class)
public class Group {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student monitor;

    @Basic
    private LocalDate entranceDate;

    @Basic
    private LocalDate graduateDate;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Student> students;

    public String toString() {
        return this.getId();
    }

}
