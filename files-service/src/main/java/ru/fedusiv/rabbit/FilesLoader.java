package ru.fedusiv.rabbit;

import org.springframework.amqp.core.Message;

public interface FilesLoader {

    String saveFile(Message message, String folder);

}
