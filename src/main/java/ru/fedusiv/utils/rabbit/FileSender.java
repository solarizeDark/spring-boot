package ru.fedusiv.utils.rabbit;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;

public interface FileSender {

    void send(MultipartFile[] files);

}
