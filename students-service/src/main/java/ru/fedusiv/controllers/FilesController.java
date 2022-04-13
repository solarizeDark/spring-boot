package ru.fedusiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.fedusiv.entities.File;
import ru.fedusiv.utils.rabbit.FileSender;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilesController {

    @Autowired
    private FileSender fileSender;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${files-service.name}")
    private String filesMicroServiceName;

    @PostMapping("/files")
    public void uploadFiles(@RequestParam("files") MultipartFile[] files) {
        fileSender.send(files);
    }

    @GetMapping("/user/files/{id}")
    public List<File> getFilesByUserId(@PathVariable("id") Long id) {

        return restTemplate.getForObject(
                String.format("http://%s/files-api/user/files/{id}", filesMicroServiceName),
                ArrayList.class,
                Long.toString(id)
        );
    }

    @GetMapping("/files")
    public String getFilesUploadingPage() {
        return "files";
    }

}
