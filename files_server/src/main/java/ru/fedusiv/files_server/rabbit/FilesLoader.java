package ru.fedusiv.files_server.rabbit;

import org.springframework.amqp.core.Message;

public interface FilesLoader {

    void saveFile(Message message, String folder);

}
