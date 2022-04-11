package ru.fedusiv.rabbit;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.fedusiv.redis.FilesRepository;

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

    public void savesStorageAndFilesystem(Message message, String folder) throws ExecutionException, InterruptedException {

        ListenableFuture<String> fileName =
                listeningExecutorService.submit(() -> filesLoader.saveFile(message, folder));

        Futures.addCallback(fileName, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                try {
                    filesRepository.save(
                            fileName.get(),
                            Long.valueOf(message.getMessageProperties().getHeader("id").toString()),
                            message.getMessageProperties().getHeader("filename")
                    );
                } catch (InterruptedException | ExecutionException e) {
                    throw new IllegalArgumentException(e);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                throw new IllegalArgumentException(throwable);
            }
        }, listeningExecutorService);

    }

    @RabbitListener(queues = "files_audio", concurrency = "4")
    public void receiveAudio(Message message) throws ExecutionException, InterruptedException {
        savesStorageAndFilesystem(message, "audios");
    }

    @RabbitListener(queues = "files_text", concurrency = "4")
    public void receiveText(Message message) throws ExecutionException, InterruptedException {
        savesStorageAndFilesystem(message, "texts");
    }

    @RabbitListener(queues = "files_image", concurrency = "4")
    public void receiveImage(Message message) throws ExecutionException, InterruptedException {
        savesStorageAndFilesystem(message, "images");
    }

    @RabbitListener(queues = "files_video", concurrency = "4")
    public void receiveVideo(Message message) throws ExecutionException, InterruptedException {
        savesStorageAndFilesystem(message, "videos");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        log.info("<---------STARTED--------->");
    }

}
