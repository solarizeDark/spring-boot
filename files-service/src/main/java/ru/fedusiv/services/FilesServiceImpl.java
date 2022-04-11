package ru.fedusiv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.fedusiv.entities.File;
import ru.fedusiv.repositories.FilesRepository;

import java.util.List;

@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    @Qualifier("postgres")
    private FilesRepository filesRepository;

    @Override
    public List<File> getFilesByUserId(Long id) {
        return filesRepository.getFilesByUserId(id);
    }

}
