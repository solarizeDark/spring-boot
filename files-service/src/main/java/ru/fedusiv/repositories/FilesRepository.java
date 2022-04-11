package ru.fedusiv.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fedusiv.entities.File;

import java.util.List;

@Repository
@Qualifier("postgres")
public interface FilesRepository extends JpaRepository<File, Long> {

    List<File> getFilesByUserId(Long userId);

}
