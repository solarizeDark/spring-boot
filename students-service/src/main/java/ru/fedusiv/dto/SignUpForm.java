package ru.fedusiv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import ru.fedusiv.validation.annotations.FieldsNonEquality;
import ru.fedusiv.validation.annotations.ValidInitials;
import ru.fedusiv.validation.annotations.ValidPassword;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldsNonEquality(
        message = "{signUp.UsernamePasswordEquality}",
        field1 = "password",
        field2 = "username"
)
public class SignUpForm {

    @NotNull
    @Email(message = "{signUp.incorrectEmail}")
    private String email;

    @NotNull
    @ValidPassword(message = "{signUp.invalidPassword}")
    private String password;

    @NotNull
    @ValidInitials(message = "{signUp.invalidUsername}")
    private String username;

}
