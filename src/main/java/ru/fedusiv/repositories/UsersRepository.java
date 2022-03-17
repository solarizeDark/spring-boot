package ru.fedusiv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fedusiv.entities.Student;
import ru.fedusiv.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
