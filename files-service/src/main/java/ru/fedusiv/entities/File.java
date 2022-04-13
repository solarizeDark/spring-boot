package ru.fedusiv.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "files")
public class File {

    @Id
    @SequenceGenerator(
            name = "files_id_sequence",
            sequenceName = "files_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "files_id_sequence"
    )
    private Long id;

    private String filename;

    private String location;

    private Long userId;

    private LocalDate created;

}
