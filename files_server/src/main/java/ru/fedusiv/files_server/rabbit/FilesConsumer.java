package ru.fedusiv.files_server.rabbit;

import org.springframework.amqp.core.Message;

public interface FilesConsumer {

    void receiveText(Message message);
    void receiveAudio(Message message);
    void receiveVideo(Message message);
    void receiveImage(Message message);

}
