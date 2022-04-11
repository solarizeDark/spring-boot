package ru.fedusiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.fedusiv.entities.File;
import ru.fedusiv.services.FilesService;

import java.util.List;

@RestController
@RequestMapping("/file-api")
public class FilesController {

    @Autowired
    private FilesService filesService;

    @GetMapping("/user/files/{id}")
    public List<File> getFilesByUserId(@PathVariable("id") Long id) {
        return filesService.getFilesByUserId(id);
    }

}
