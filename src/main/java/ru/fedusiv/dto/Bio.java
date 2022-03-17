package ru.fedusiv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bio {

    private String name;

    private String surname;

    private Integer age;

    private String group;

}
