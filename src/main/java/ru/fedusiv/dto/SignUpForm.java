package ru.fedusiv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import ru.fedusiv.validation.annotations.UsernamePasswordNonEquality;
import ru.fedusiv.validation.annotations.ValidPassword;
import ru.fedusiv.validation.annotations.ValidUsername;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@UsernamePasswordNonEquality(
        message = "{signUp.UsernamePasswordEquality}",
        password = "password",
        username = "username"
)
public class SignUpForm {

    @Email(message = "{signUp.incorrectEmail}")
    private String email;

    @ValidPassword(message = "{signUp.invalidPassword}")
    private String password;

    @ValidUsername(message = "{signUp.invalidUsername}")
    private String username;

}
