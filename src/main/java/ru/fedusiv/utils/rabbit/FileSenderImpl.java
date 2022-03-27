package ru.fedusiv.utils.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class FileSenderImpl implements FileSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void send(MultipartFile[] files) {

        for (MultipartFile file: files) {
            try {
                amqpTemplate.convertAndSend("spring-boot-exchange", "files.portion", new Message(String.valueOf(10).getBytes(StandardCharsets.UTF_8)));
                amqpTemplate.convertAndSend("spring-boot-exchange", "files.portion", new Message(file.getOriginalFilename().getBytes(StandardCharsets.UTF_8)));
                amqpTemplate.convertAndSend("spring-boot-exchange", "files.portion", new Message(file.getBytes()));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

}
