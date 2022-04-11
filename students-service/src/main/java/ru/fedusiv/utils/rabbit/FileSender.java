package ru.fedusiv.utils.rabbit;

import org.springframework.web.multipart.MultipartFile;

public interface FileSender {

    void send(MultipartFile[] files);

}
