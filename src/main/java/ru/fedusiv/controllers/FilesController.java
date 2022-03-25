package ru.fedusiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.fedusiv.utils.rabbit.FileSender;

import java.util.Arrays;

@Controller
public class FilesController {

    @Autowired
    private FileSender fileSender;

    @PostMapping("/files")
    public void uploadFiles(@RequestParam("files") MultipartFile[] files) {

        Arrays.stream(files).forEach(file -> System.out.println(file.getName()));
        fileSender.send(files);

    }

    @GetMapping("/files")
    public String getFilesUploadingPage() {
        return "files";
    }

}
