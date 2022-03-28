package ru.fedusiv.files_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fedusiv.files_server.rabbit.FilesLoader;
import ru.fedusiv.files_server.rabbit.FilesLoaderImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfig {

    @Bean
    public FilesLoader filesLoader() {
        return new FilesLoaderImpl();
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

}
