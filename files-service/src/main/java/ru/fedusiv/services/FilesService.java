package ru.fedusiv.services;

import org.springframework.stereotype.Service;
import ru.fedusiv.entities.File;

import java.util.List;

public interface FilesService {

    List<File> getFilesByUserId(Long id);

}
