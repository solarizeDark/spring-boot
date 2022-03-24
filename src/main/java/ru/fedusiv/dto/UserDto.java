package ru.fedusiv.dto;

import lombok.*;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserDto {

    private String username;
    private String password;
    private String email;

}
