package ru.fedusiv.services.interfaces;

import org.springframework.stereotype.Service;
import ru.fedusiv.dto.UserDto;
import ru.fedusiv.entities.Student;
import ru.fedusiv.entities.User;

@Service
public interface UsersService {

    User findByUsername(String username);
    User save(UserDto userDto);
    User confirmAccount(String code);

}
