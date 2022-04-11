package ru.fedusiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.fedusiv.entities.File;
import ru.fedusiv.utils.rabbit.FileSender;

import java.util.List;

@Controller
public class FilesController {

    @Autowired
    private FileSender fileSender;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${files-service.name}")
    private String filesMicroServiceName;

    private final String filesMicroServiceUrl =
            String.format("http://{%s}/files-api", filesMicroServiceName);

    @PostMapping("/files")
    public void uploadFiles(@RequestParam("files") MultipartFile[] files) {
        fileSender.send(files);
    }

    @GetMapping("/user/files/{id}")
    public void getFilesByUserId(@PathVariable("id") Long id) {

        ResponseEntity<List<File>> filesResponse = restTemplate.exchange(
                filesMicroServiceUrl.concat("/user/files/{id}"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {},
                id
        );

        List<File> files = filesResponse.getBody();
        int a = 5;

    }

    @GetMapping("/files")
    public String getFilesUploadingPage() {
        return "files";
    }

}
