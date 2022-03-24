package ru.fedusiv.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fedusiv.dto.UserDto;
import ru.fedusiv.entities.Role;
import ru.fedusiv.entities.Status;
import ru.fedusiv.entities.User;
import ru.fedusiv.repositories.UsersRepository;
import ru.fedusiv.services.interfaces.UsersService;

import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public User save(UserDto userDto) {
        User user = User.builder()
                .hashedPassword(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .role(Role.STUDENT)
                .status(Status.UNCONFIRMED)
                .username(userDto.getUsername())
                .confirmationCode(UUID.randomUUID().toString())
                .build();
        return usersRepository.save(user);
    }

    @Override
    public User confirmAccount(String code) {
        User user = usersRepository.getByConfirmationCode(code);
        user.setStatus(Status.CONFIRMED);
        usersRepository.save(user);
        return user;
    }

}
