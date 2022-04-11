package ru.fedusiv.rabbit;

import org.springframework.amqp.core.Message;

import java.util.concurrent.ExecutionException;

public interface FilesConsumer {

    void receiveText(Message message) throws ExecutionException, InterruptedException;
    void receiveAudio(Message message) throws ExecutionException, InterruptedException;
    void receiveVideo(Message message) throws ExecutionException, InterruptedException;
    void receiveImage(Message message) throws ExecutionException, InterruptedException;

}
