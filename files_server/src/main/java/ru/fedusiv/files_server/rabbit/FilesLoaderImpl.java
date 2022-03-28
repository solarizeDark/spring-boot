package ru.fedusiv.files_server.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class FilesLoaderImpl implements FilesLoader {

    @Override
    public void saveFile(Message message, String folder) {
        try {
            String name = message.getMessageProperties().getHeader("filename");

            File file = new File("files\\" + folder + "\\" + UUID.randomUUID() + "_" + name);
            log.info(name + " saved");

            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            bufferedOutputStream.write(message.getBody());
            bufferedOutputStream.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
