package ru.fedusiv.entities;

import lombok.*;

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
