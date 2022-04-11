package ru.fedusiv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fedusiv.entities.Group;

@Repository
public interface GroupsRepository extends JpaRepository<Group, String> {

}
