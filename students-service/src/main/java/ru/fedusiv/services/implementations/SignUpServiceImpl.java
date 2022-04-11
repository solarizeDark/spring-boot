package ru.fedusiv.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fedusiv.dto.SignUpForm;
import ru.fedusiv.entities.Role;
import ru.fedusiv.entities.Status;
import ru.fedusiv.entities.User;
import ru.fedusiv.repositories.UsersRepository;
import ru.fedusiv.services.interfaces.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(SignUpForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .hashedPassword(passwordEncoder.encode(form.getPassword()))
                .username(form.getUsername())
                .role(Role.STUDENT)
                .status(Status.UNCONFIRMED)
                .build();

        usersRepository.save(user);
    }
}
