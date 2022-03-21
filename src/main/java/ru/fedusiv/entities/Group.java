package ru.fedusiv.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import ru.fedusiv.json.GroupJsonSerializer;
import ru.fedusiv.json.StudentJsonSerializer;
import ru.fedusiv.json.TemplateJsonSerializer;

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
@JsonSerialize(using = GroupJsonSerializer.class)
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
