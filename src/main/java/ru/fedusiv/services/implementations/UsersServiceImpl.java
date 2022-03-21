package ru.fedusiv.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedusiv.entities.User;
import ru.fedusiv.repositories.UsersRepository;
import ru.fedusiv.services.interfaces.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        usersRepository.save(user);
    }

}
