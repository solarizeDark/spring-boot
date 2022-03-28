package ru.fedusiv.files_server.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
@Slf4j
public class FilesConsumerImpl implements FilesConsumer {

    @Autowired
    private FilesLoader filesLoader;

    @Autowired
    private ExecutorService executorService;

    @RabbitListener(queues = "files_audio", concurrency = "4")
    public void receiveAudio(Message message) {
        executorService.submit(() -> filesLoader.saveFile(message, "audios"));
    }

    @RabbitListener(queues = "files_text", concurrency = "4")
    public void receiveText(Message message) {
        executorService.submit(() -> filesLoader.saveFile(message, "texts"));
    }

    @RabbitListener(queues = "files_image", concurrency = "4")
    public void receiveImage(Message message) {
        executorService.submit(() -> filesLoader.saveFile(message, "images"));
    }

    @RabbitListener(queues = "files_video", concurrency = "4")
    public void receiveVideo(Message message) {
        executorService.submit(() -> filesLoader.saveFile(message, "videos"));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        log.info("<---------STARTED--------->");
    }

}
