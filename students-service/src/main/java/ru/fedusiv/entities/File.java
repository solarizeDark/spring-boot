package ru.fedusiv.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class File {

    private Long userId;
    private Long fileId;
    private String name;
    private LocalDate created;

}
