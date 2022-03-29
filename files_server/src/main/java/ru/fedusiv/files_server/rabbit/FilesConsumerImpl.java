package ru.fedusiv.files_server.rabbit;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.fedusiv.files_server.redis.FilesRepository;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class FilesConsumerImpl implements FilesConsumer {

    @Autowired
    private FilesLoader filesLoader;

    @Autowired
    private ListeningExecutorService listeningExecutorService;

    @Autowired
    private FilesRepository filesRepository;

    public void savesStorageAndFilesystem(Message message, String type) throws ExecutionException, InterruptedException {

        ListenableFuture<String> fileName =
                listeningExecutorService.submit(() -> filesLoader.saveFile(message, type + "s"));
        filesRepository.save(
                fileName.get(),
                Long.valueOf(message.getMessageProperties().getHeader("id").toString()),
                message.getMessageProperties().getHeader("filename")
        );
    }

    @RabbitListener(queues = "files_audio", concurrency = "4")
    public void receiveAudio(Message message) throws ExecutionException, InterruptedException {
        savesStorageAndFilesystem(message, "audio");
    }

    @RabbitListener(queues = "files_text", concurrency = "4")
    public void receiveText(Message message) throws ExecutionException, InterruptedException {
        savesStorageAndFilesystem(message, "text");
    }

    @RabbitListener(queues = "files_image", concurrency = "4")
    public void receiveImage(Message message) throws ExecutionException, InterruptedException {
        savesStorageAndFilesystem(message, "image");
    }

    @RabbitListener(queues = "files_video", concurrency = "4")
    public void receiveVideo(Message message) throws ExecutionException, InterruptedException {
        savesStorageAndFilesystem(message, "video");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        log.info("<---------STARTED--------->");
    }

}
