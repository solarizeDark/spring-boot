package ru.fedusiv.files_server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.fedusiv.files_server.rabbit.FilesConsumer;
import ru.fedusiv.files_server.rabbit.FilesConsumerImpl;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class FilesServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesServerApplication.class, args);
    }

}
