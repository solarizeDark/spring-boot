package ru.fedusiv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedusiv.entities.Student;
import ru.fedusiv.entities.User;
import ru.fedusiv.repositories.UsersRepository;

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
