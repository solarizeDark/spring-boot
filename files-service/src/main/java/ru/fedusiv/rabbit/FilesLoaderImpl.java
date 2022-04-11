package ru.fedusiv.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class FilesLoaderImpl implements FilesLoader {

    @Value("${files.storage.path}")
    private String fileStoragePath;

    @Override
    public String saveFile(Message message, String folder) {

        String name = message.getMessageProperties().getHeader("filename");
        String tag = UUID.randomUUID().toString();

        String fileName = tag + "_" + name;

        File file = new File(fileStoragePath + File.separator + folder + File.separator + fileName);

        log.info("can write: " + file.canWrite());

        try (BufferedOutputStream bufferedOutputStream
                     = new BufferedOutputStream(new FileOutputStream(file))) {

            byte[] mFile = message.getBody();

            log.info("message length: " + mFile.length);

            bufferedOutputStream.write(mFile);
            bufferedOutputStream.flush();
            log.info(name + " saved");

            return fileName;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
